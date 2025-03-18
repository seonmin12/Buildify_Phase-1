package domain.Inbound.controller;

import common.ValidCheck;
import domain.Inbound.service.InboundSearchService;
import dto.InboundDto;

import java.util.List;

public class InboundSearchControllerImp implements InboundSearchController{
    private final InboundSearchService inboundSearchService;
    private final ValidCheck validCheck;

    public InboundSearchControllerImp(InboundSearchService inboundSearchServicem, ValidCheck validCheck){
        this.inboundSearchService = inboundSearchServicem;
        this.validCheck = validCheck;
    }



    @Override
    public void userSearchAll() {
        List<InboundDto> inboundDtoList = inboundSearchService.userSearchAll();
        for(InboundDto inboundDto : inboundDtoList){
            System.out.printf("입고번호: %s | 상품ID: %s | 고객ID: %s | 수량: %d | 상태: %d | 요청일: %s | 창고ID: %s\n",
                    inboundDto.getInbound_number(),inboundDto.getProd_id(), inboundDto.getClient_id(),inboundDto.getQuantity(),
                    inboundDto.getInbound_status(), inboundDto.getReq_inbound_day(),inboundDto.getWare_id());
        }


    }

    @Override
    public void SearchAll() {
        List<InboundDto> inboundDtoList = inboundSearchService.SearchAll();

        for(InboundDto inboundDto : inboundDtoList){
            System.out.printf("입고번호: %s | 상품ID: %s | 고객ID: %s | 수량: %d | 상태: %d | 요청일: %s | 창고ID: %s\n",
                    inboundDto.getInbound_number(),inboundDto.getProd_id(), inboundDto.getClient_id(),inboundDto.getQuantity(),
                    inboundDto.getInbound_status(), inboundDto.getReq_inbound_day(),inboundDto.getWare_id());
        }
    }

    @Override
    public void SearchOne() {
        System.out.println("조회할 업체 입력: ");
        String a = validCheck.inputAnyString();
        List<InboundDto> inboundDtoList = inboundSearchService.SearchOne(a);
        for(InboundDto inboundDto : inboundDtoList){
            System.out.printf("입고번호: %s | 상품ID: %s | 고객ID: %s | 수량: %d | 상태: %d | 요청일: %s | 창고ID: %s\n",
                    inboundDto.getInbound_number(),inboundDto.getProd_id(), inboundDto.getClient_id(),inboundDto.getQuantity(),
                    inboundDto.getInbound_status(), inboundDto.getReq_inbound_day(),inboundDto.getWare_id());
        }



    }


}