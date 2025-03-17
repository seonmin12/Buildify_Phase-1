package domain.Outbound.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import static config.DBConnection.getConnection;

public class rejectOutbound {
    public void rejectOutbound(String outboundNumber, String prodId) throws SQLException {
        try (Connection conn = getConnection()) {
            CallableStatement cs = conn.prepareCall("{call sp_rejectOutbound(?, ?)}");
            cs.setString(1, outboundNumber);
            cs.setString(2, prodId);
            cs.execute();
            cs.close();
        }
    }

}
