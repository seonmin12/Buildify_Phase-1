package domain.DH_UserManagement.repository;

import config.DBConnection;
import dto.UserDto;

import java.sql.*;

public class SignUpRepositoryImpl implements SignUpRepository {

    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

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
            cs.setInt(5, userDto.getBusiness_number());
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
            } else if(rtncode == 400){
                connection.rollback(); // 실패 시 롤백
                System.out.println("회원가입 실패! rtncode: " + rtncode);
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
