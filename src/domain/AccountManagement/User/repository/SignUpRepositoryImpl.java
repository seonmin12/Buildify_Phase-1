package domain.AccountManagement.User.repository;

import config.DBConnection;
import dto.UserDto;

import java.sql.*;

/**
 * {@link SignUpRepository}의 구현체.
 *
 * <p>사용자 회원가입 및 ID 중복 검사를 수행하는 리포지토리 클래스입니다.</p>
 *
 * <p>회원가입 시, 저장 프로시저 `SignUp`을 호출하며, 성공 또는 실패 여부를 반환합니다.
 * 또한, ID 중복 여부를 확인하는 기능을 제공합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class SignUpRepositoryImpl implements SignUpRepository {

    private final Connection connection = DBConnection.getConnection();
    private CallableStatement cs = null;

    /**
     * 새로운 사용자를 데이터베이스에 추가합니다.
     *
     * <p>회원가입은 저장 프로시저 `SignUp`을 호출하여 수행됩니다.
     * 회원가입이 성공하면 커밋되고, 실패하면 롤백됩니다.</p>
     *
     * @param userDto 등록할 사용자 정보를 포함한 {@link UserDto} 객체
     * @return 회원가입이 성공하면 {@code true}, 실패하면 {@code false}
     */
    @Override
    public boolean InsertUser(UserDto userDto) {
        boolean isSuccess = false;

        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{ CALL SignUp(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }"); // 11개 파라미터 (client_id 제거됨)

            // IN 파라미터 설정 (client_id 제외)
            cs.setString(1, userDto.getUser_name());
            cs.setString(2, userDto.getUser_phone());
            cs.setString(3, userDto.getUser_email());
            cs.setString(4, userDto.getUser_adress());
            cs.setString(5, userDto.getBusiness_number());
            cs.setString(6, userDto.getUser_id());
            cs.setString(7, userDto.getUser_pw());
            cs.setInt(8, userDto.getUser_status());
            cs.setBigDecimal(9, userDto.getUser_ware_size());
            cs.setBigDecimal(10, userDto.getUser_ware_use());

            // OUT 파라미터 설정 (rtncode 반환)
            cs.registerOutParameter(11, Types.INTEGER);

            // 실행
            cs.execute();

            // OUT 파라미터 값 가져오기
            int rtncode = cs.getInt(11);
            System.out.println("rtncode: " + rtncode);

            if (rtncode == 200) {
                isSuccess = true;
                connection.commit(); // 데이터 반영
                System.out.println("회원가입 성공! 데이터가 저장되었습니다.");
            } else {
                connection.rollback(); // 실패 시 롤백
                System.out.println("회원가입 실패! rtncode: " + rtncode);
            }

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.err.println("SQL 오류 발생: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (cs != null) cs.close();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isSuccess;
    }

    /**
     * 주어진 사용자 ID가 데이터베이스에 이미 존재하는지 확인합니다.
     *
     * @param userid 중복 여부를 확인할 사용자 ID
     * @return 중복된 ID가 있으면 {@code true}, 없으면 {@code false}
     */
    @Override
    public boolean duplicateCheckUserID(String userid) {
        boolean exists = false;
        String sql = "SELECT COUNT(*) FROM user WHERE user_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }
}
