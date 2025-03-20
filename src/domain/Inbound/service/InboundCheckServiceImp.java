package domain.Inbound.service;

import domain.Inbound.repository.InboundCheckRepo;
import domain.Inbound.repository.InboundSearchRepo;
import dto.InboundDto;

import java.util.List;

public class InboundCheckServiceImp implements InboundCheckService{

    private final InboundCheckRepo inboundCheckRepo;

    public InboundCheckServiceImp(InboundCheckRepo inboundCheckRepo) {
        this.inboundCheckRepo = inboundCheckRepo;
    }

    /**
     * 관리자 입고요청 전체확인
     * @return
     */
    @Override
    public List<InboundDto> allCheckRead() {
        return inboundCheckRepo.allCheckRead();

    }

    /**
     * 관리자 입고요청 전체승인
     */
    @Override
    public void allCheckUpdate() {
        inboundCheckRepo.allCheckUpdate();
    }

    /**
     * 관리자 입고요청 전체반려
     */
    @Override
    public void allCheckReturn() {
        inboundCheckRepo.allCheckReturn();

    }

    /**
     * 관리자 입교요청 업체별 확인
     * @param ci
     * @return
     */
    @Override
    public List<InboundDto> clientCheckRead(String ci) {
        return inboundCheckRepo.clientCheckRead(ci);
    }

    /**
     * 관리자 입고요청 전체승인
     * @param ci
     */
    @Override
    public void clientCheckUpdate(String ci) {
        inboundCheckRepo.clientCheckUpdate(ci);

    }

    /**
     * 관리자 입고요청 전체반려
     * @param ci
     */
    @Override
    public void clientCheckReturn(String ci) {
        inboundCheckRepo.clientCheckReturn(ci);

    }

    /**
     * 관리자 입고요청 개별승인
     * @param ci
     */
    @Override
    public void numCheckUpdate(String ci) {
        inboundCheckRepo.inbound_number_check_update(ci);


    }

    /**
     * 관리자 입고요청 전체반려
     * @param ci
     */
    @Override
    public void numCheckReturn(String ci) {
        inboundCheckRepo.inbound_number_check_return(ci);

    }

}



