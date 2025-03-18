package domain.Outbound.repository;

import domain.Inventory.repository.InventoryReadRepo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import static config.DBConnection.getConnection;

public class OutboundMemberRequestRepositoryImpl implements OutboundmemberRequestRepository{
    OutboundMemberRequestRepositoryImpl repository = null;

    //회원이 총관리자에게 승인 요청을 하는 기능
    //회원이 관리자에게 출고 요구한다
    public void createOutbound(String outboundNumber, String prodId, String clientId, int quantity, String wareId) throws SQLException {
        try (Connection conn = getConnection()) {
            CallableStatement cs = conn.prepareCall("{call sp_createOutbound(?, ?, ?, ?, ?)}");
            cs.setString(1, outboundNumber);
            cs.setString(2, prodId);
            cs.setString(3, clientId);
            cs.setInt(4, quantity);
            cs.setString(5, wareId);
            cs.execute();
            cs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
