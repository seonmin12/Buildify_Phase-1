package domain.AccountManagement.User.repository;

import config.DBConnection;
import dto.UserDto;

import java.sql.*;

public class UpdateUserinfoRepositoryImpl implements UpdateUserinfoRepository {

    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

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
