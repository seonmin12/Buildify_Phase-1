package domain.Outbound.service;

import domain.Outbound.repository.OutboundMemberRequestRepositoryImpl;

import java.sql.SQLException;
import domain.Outbound.repository.OutboundMemberRequestRepositoryImpl;

public class OutboundMemberRequsetServiceImpl implements OutboundMemberRequsetService {
    OutboundMemberRequestRepositoryImpl outbound_repo = null;
    //회원이 관리자에게 출고 요구한다
    public boolean createOutboundRequest(String prodId, String clientId, int quantity, String wareId) {
        try {
            String outboundNumber = "out" + System.currentTimeMillis();
            //OutboundMemberRequestRepositoryImpl repository = null;


            outbound_repo.createOutbound(outboundNumber, prodId, clientId, quantity, wareId);
            return true;
        } catch (SQLException e) {
            System.out.println("출고 요청 실패: " + e.getMessage());
            return false;
        }
    }
}
