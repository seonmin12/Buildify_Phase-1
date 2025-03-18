package domain.Outbound.repository;

import config.DBConnection;

import java.sql.*;

import static config.DBConnection.getConnection;

public class OutboundAdminApproveRepositoryImpl implements OutboundAdminApproveRepository {
    public void approveOutbound(String outboundNumber, String prodId){
        try (Connection conn = getConnection()) {
            CallableStatement cs = conn.prepareCall("{call sp_approveOutbound(?, ?)}");
            cs.setString(1, outboundNumber);
            cs.setString(2, prodId);
            cs.execute();
            cs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }





}
