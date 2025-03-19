package domain.AccountManagement.Admin.repository;

import config.DBConnection;
import dto.AdminDto;

import java.sql.*;

public class LoginRepositoryImpl implements LoginRepository {

    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;

    PreparedStatement pstmt = null;

    /**
     * id를 넣고 일치하는 관리자 정보를 DB에서 가져오는 Repository
     * @param id
     * @return AdminDto
     */
    @Override
    public AdminDto login(String id) {
        AdminDto adminDto = null;
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call DB_ADMIN_READONE(?)}");
            cs.setString(1,id);
            rs = cs.executeQuery();

            if (rs.next()) {
                adminDto = AdminDto.builder()
                        .adminNumber(rs.getString("admin_number"))
                        .adminRole(rs.getString("admin_role"))
                        .adminName(rs.getString("admin_name"))
                        .adminEmail(rs.getString("admin_email"))
                        .adminEnterday(rs.getDate("admin_enterday"))
                        .adminAddress(rs.getString("admin_adress"))
                        .adminPhone(rs.getString("admin_phone"))
                        .adminId(rs.getString("admin_id"))
                        .adminPw(rs.getString("admin_pw"))
                        .build();

                cs.close();
                return adminDto;
            } else return null;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return adminDto;
    }
}