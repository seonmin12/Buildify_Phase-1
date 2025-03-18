package domain.AccountManagement.User.repository;

import config.DBConnection;
import dto.UserDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateUserinfoRepositoryImpl implements UpdateUserinfoRepository {

    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    @Override
    public boolean updateUserinfo(String clientId, int updateOption, String newValue) {
        boolean isSuccess = false;
        String sql = "";

        switch (updateOption) {
            case 1:
                sql = "UPDATE user SET user_name = ? WHERE client_id = ?";
                break;
            case 2:
                sql = "UPDATE user SET user_phone = ? WHERE client_id = ?";
                break;
            case 3:
                sql = "UPDATE user SET user_email = ? WHERE client_id = ?";
                break;
            case 4:
                sql = "UPDATE user SET user_adress = ? WHERE client_id = ?";
                break;
            case 5:
                sql = "UPDATE user SET user_pw = ? WHERE client_id = ?";
                break;
            default:
                System.out.println("잘못된 선택입니다.");
                break;
        }

        try{
            pstmt = connection.prepareCall(sql);
            pstmt.setString(1, newValue);
            pstmt.setString(2, clientId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                isSuccess = true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return isSuccess;
    }
}
