package domain.Outbound.service;

import domain.Outbound.repository.OutboundAdminApproveRepositoryImpl;
import domain.Outbound.repository.OutboundMemberRequestRepositoryImpl;

import java.sql.SQLException;

public class OutboundAdminApproveServiceImpl {
    OutboundAdminApproveRepositoryImpl outbound_admin_approve_repo = null;
    public boolean approveOutbound(String outboundNumber, String prodId) {
        outbound_admin_approve_repo.approveOutbound(outboundNumber, prodId);
        return true;


    }
}
