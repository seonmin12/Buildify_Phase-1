package domain.Outbound.repository;

import java.sql.SQLException;

public interface rejectOutboundImpl {//관리자가 승인 거부하는 기능
    void rejectOutbound(String outboundNumber, String prodId) throws SQLException;
}
