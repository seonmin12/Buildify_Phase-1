package domain.Inbound.service;

import common.ErrorCode;
import domain.Inbound.repository.InboundSearchRepo;
import dto.ClientUpdateDto;
import dto.InboundDto;
import dto.UserDto;
import exception.InboundException;
import exception.NotFoundException;

import java.util.List;

public class InboundSearchServiceImp implements InboundSearchService{

    private final InboundSearchRepo inboundSearchRepo;

    public InboundSearchServiceImp(InboundSearchRepo inboundSearchRepo){

        this.inboundSearchRepo = inboundSearchRepo;
    }


    /**
     * 입고요청진행 업체리스트 조회
     * @return
     */
    @Override
    public List<ClientUpdateDto> clientupdatesearch() {
        return inboundSearchRepo.clientsearch();
    }

    /**
     * 회원 입고요청 리스트 업체별 조회
     * @param a
     * @return
     */
    @Override
    public List<InboundDto> userSearchAll(String a) {
        return inboundSearchRepo.userSearch(a);
    }

    /**
     관리자 입고요청 리스트 업체별 조회
     *
     * @param inbound_number
     * @return
     */
    @Override
    public List<InboundDto> SearchOne(String inbound_number) {
        try{
            return inboundSearchRepo.SearchOne(inbound_number);
        }catch (InboundException e){
            throw new InboundException(ErrorCode.ERROR_INPUT);
        }

    }

    /**
     * 관리자 입고요청 리스트 전체조회
     * @return
     */
    @Override
    public List<InboundDto> SearchAll() {
        try{
            return inboundSearchRepo.SearchAll().orElseThrow(() -> new NotFoundException(String.valueOf(ErrorCode.ERROR_INPUT)));
        }catch(InboundException e){
            throw new InboundException(ErrorCode.ERROR_INPUT);
        }
    }
}