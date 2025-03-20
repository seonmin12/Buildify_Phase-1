package domain.AccountManagement.User.repository;

import config.DBConnection;
import dto.UserDto;

import java.sql.*;

/**
 * {@link UpdateUserinfoRepository}의 구현체.
 *
 * <p>사용자의 정보를 업데이트하고 최신 정보를 조회하는 기능을 수행하는 리포지토리 클래스입니다.</p>
 *
 * <p>사용자 정보 업데이트는 저장 프로시저 `UpdateUserinfo`를 호출하여 수행되며,
 * 업데이트된 사용자 정보를 조회할 수도 있습니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class UpdateUserinfoRepositoryImpl implements UpdateUserinfoRepository {

    private final Connection connection = DBConnection.getConnection();

    /**
     * 특정 사용자의 정보를 업데이트합니다.
     *
     * <p>저장 프로시저 `UpdateUserinfo`를 호출하여 특정 사용자의 정보를 변경합니다.
     * 성공 여부는 반환 코드 (`rtncode`)를 통해 확인됩니다.</p>
     *
     * @param clientId 정보를 업데이트할 사용자 클라이언트 ID
     * @param updateOption 업데이트할 항목의 옵션 (예: 1 - 이름, 2 - 전화번호, 3 - 이메일 등)
     * @param newValue 업데이트할 새로운 값
     * @return 업데이트가 성공하면 {@code true}, 실패하면 {@code false}
     */
    @Override
    public boolean updateUserinfo(String clientId, int updateOption, String newValue) {
        boolean isSuccess = false;
        String sql = "{CALL UpdateUserinfo(?, ?, ?, ?)}"; // 🔥 프로시저 호출

        try (Connection connection = DBConnection.getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {

            cs.setString(1, clientId);
            cs.setInt(2, updateOption);
            cs.setString(3, newValue);

            cs.registerOutParameter(4, java.sql.Types.INTEGER);

            cs.execute();

            int rtncode = cs.getInt(4);
            if (rtncode == 200) {
                isSuccess = true;
            } else {
                System.out.println("업데이트 실패 (rtncode: " + rtncode + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    /**
     * 특정 사용자의 최신 정보를 조회합니다.
     *
     * <p>사용자의 정보를 조회하여 {@link UserDto} 객체로 반환합니다.
     * 사용자가 존재하지 않으면 {@code null}을 반환합니다.</p>
     *
     * @param clientId 조회할 사용자 클라이언트 ID
     * @return 업데이트된 사용자 정보가 포함된 {@link UserDto} 객체, 사용자가 존재하지 않으면 {@code null}
     * @throws RuntimeException SQL 실행 중 예외 발생 시 예외 처리
     */
    @Override
    public UserDto getUpdatedUserinfo(String clientId) {
        UserDto userDto = null;
        String sql = "SELECT * FROM user WHERE client_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, clientId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userDto = UserDto.builder()
                        .client_id(rs.getString("client_id"))
                        .user_name(rs.getString("user_name"))
                        .user_phone(rs.getString("user_phone"))
                        .user_email(rs.getString("user_email"))
                        .user_adress(rs.getString("user_adress"))
                        .business_number(rs.getString("business_number"))
                        .user_enterday(rs.getDate("user_enterday"))
                        .user_status(rs.getInt("user_status"))
                        .user_ware_size(rs.getBigDecimal("user_ware_size"))
                        .user_ware_use(rs.getBigDecimal("user_ware_use"))
                        .build();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userDto;
    }
}
