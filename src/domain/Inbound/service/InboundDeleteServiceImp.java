package domain.Inbound.service;

import common.ErrorCode;
import domain.Inbound.repository.InboundDeleteRepo;
import domain.Inbound.repository.InboundDeleteRepoImp;
import domain.Inbound.repository.InboundSearchRepo;
import dto.InboundDto;
import exception.InboundException;

public class InboundDeleteServiceImp implements InboundDeleteService{
    private final InboundDeleteRepo inboundDeleteRepo;
    private final InboundSearchRepo inboundSearchRepo;

    public InboundDeleteServiceImp(InboundDeleteRepo inboundDeleteRepo, InboundSearchRepo inboundSearchRepo) {
        this.inboundDeleteRepo = inboundDeleteRepo;
        this.inboundSearchRepo = inboundSearchRepo;
    }

    @Override
    public void Delete(String inbound_number)  {
        inboundDeleteRepo.Delete(inbound_number);
//        InboundDto inboundDto = inboundSearchRepo.SearchOne(inbound_number).orElseThrow(()->new InboundException(ErrorCode.ERROR_INPUT));
//        try{
//            inboundDeleteRepo.Delete(InboundDto.builder()
//            .inbound_number(inboundDto.getInbound_number())
//                    .prod_id(inboundDto.getProd_id())
//                    .client_id(inboundDto.getClient_id())
//                    .quantity(inboundDto.getQuantity())
//                    .inbound_status(inboundDto.getInbound_status())
//                    .req_inbound_day(inboundDto.getReq_inbound_day())
//                    .ware_id(inboundDto.getWare_id())
//                    .build());
//        }catch (InboundException e){
//            throw new InboundException(ErrorCode.ERROR_INPUT);
//        }

    }
}
