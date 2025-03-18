package domain.Outbound.service;

import common.ErrorCode;
import domain.Outbound.repository.OutboundUserRepository;
import domain.Outbound.repository.OutboundUserRepositoryImpl;
import dto.OutboundDto;
import exception.OutboundException;

import java.util.ArrayList;
import java.util.List;

/**
 * (관리자용) 출고관리 Service 구현체입니다.
 */
public class OutboundUserServiceImpl implements OutboundUserService {
    private OutboundUserRepository outboundUserRepository;

    public OutboundUserServiceImpl(OutboundUserRepository outboundUserRepository) {
        this.outboundUserRepository = outboundUserRepository;
    }

    @Override
    public List<OutboundDto> outboundUserRead(String clientID) throws OutboundException {
        List<OutboundDto> outboundlist = outboundUserRepository.outboundUserRead(clientID);

        if(outboundlist == null || outboundlist.isEmpty()){
            throw new OutboundException(ErrorCode.DB_READ_ALL_ERROR);
        }
        return outboundlist;

    }

    @Override
    public boolean outboundUserDelete(String outboundNumber, String clientID) {
        int result = outboundUserRepository.deleteOutboundUser(outboundNumber, clientID);
        return result == 1;
    }

    @Override
    public List<OutboundDto> getOutboundRequests(String clientId) {
       return outboundUserRepository.getOutboundUserRequest(clientId);


    }
}
