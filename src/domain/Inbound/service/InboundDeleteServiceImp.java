package domain.Inbound.service;

import common.ErrorCode;
import domain.Inbound.repository.InboundDeleteRepo;
import domain.Inbound.repository.InboundDeleteRepoImp;
import domain.Inbound.repository.InboundSearchRepo;
import dto.InboundDto;
import exception.InboundException;

import java.util.List;

public class InboundDeleteServiceImp implements InboundDeleteService{
    private final InboundDeleteRepo inboundDeleteRepo;


    public InboundDeleteServiceImp(InboundDeleteRepo inboundDeleteRepo) {
        this.inboundDeleteRepo = inboundDeleteRepo;

    }

    /**
     * 회원 입고요청 삭제리스트 확인
     * @param a
     * @return
     */
    @Override
    public List<InboundDto> deletesearch(String a) {
        return inboundDeleteRepo.deleteSearch(a);
    }

    /**
     * 회원 입고요청 삭제
     * @param inbound_number
     */
    @Override
    public void Delete(String inbound_number)  {
        inboundDeleteRepo.Delete(inbound_number);


    }
}
