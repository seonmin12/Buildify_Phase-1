package domain.Inbound.controller;

import common.ValidCheck;
import domain.Inbound.service.InboundDeleteService;
import dto.InboundDto;
import dto.UserDto;

import java.util.List;
import java.util.Scanner;

public class InboundDeleteControllerImp implements InboundDeleteController{

    private final InboundDeleteService inboundDeleteService;
    private final ValidCheck validCheck;

    Scanner sc = new Scanner(System.in);
    public InboundDeleteControllerImp(InboundDeleteService inboundDeleteService, ValidCheck validCheck) {
        this.inboundDeleteService = inboundDeleteService;
        this.validCheck = validCheck;
    }

    @Override
    public void delete(UserDto userDto) {
        String status;
        String a = userDto.getClient_id();
        List<InboundDto> inboundDtoList = inboundDeleteService.deletesearch(a);
        if (inboundDtoList.isEmpty()){
            System.out.println("리스트 비어있음");
            return;
        }
        for(InboundDto inboundDto : inboundDtoList){
            if (inboundDto.getInbound_status() == 0){
                status= "대기";
            }else if (inboundDto.getInbound_status() == 1){
                status = "승인";
            }else status = "반려";
            System.out.printf("입고번호: %s | 상품ID: %s | 고객ID: %s | 수량: %d | 상태: %s | 요청일: %s | 창고ID: %s\n",
                    inboundDto.getInbound_number(),inboundDto.getProd_id(), inboundDto.getClient_id(),inboundDto.getQuantity(),
                    status, inboundDto.getReq_inbound_day(),inboundDto.getWare_id());
        }
        System.out.println("삭제할 입고번호 입력");
        String inbound_num = validCheck.inputAnyString();
        inboundDeleteService.Delete(inbound_num);
        System.out.println("");



    }
}
