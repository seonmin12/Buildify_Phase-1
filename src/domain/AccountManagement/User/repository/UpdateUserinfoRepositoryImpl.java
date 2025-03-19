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
        String sql = "{CALL UpdateUserinfo(?, ?, ?, ?)}"; // 🔥 프로시저 호출

        try (Connection connection = DBConnection.getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {

            // 🔥 IN 파라미터 설정
            cs.setString(1, clientId);
            cs.setInt(2, updateOption);
            cs.setString(3, newValue);

            cs.registerOutParameter(4, java.sql.Types.INTEGER);

            cs.execute();

            int rtncode = cs.getInt(4);
            if (rtncode == 200) {
                isSuccess = true;
            } else {
                System.out.println("❌ 업데이트 실패 (rtncode: " + rtncode + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

}
