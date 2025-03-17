package domain.Inbound.controller;

import common.ValidCheck;
import domain.Inbound.service.InboundDeleteService;
import dto.InboundDto;

import java.util.Scanner;

public class InboundDeleteControllerImp implements InboundDeleteController{

    private final InboundDeleteService inboundDeleteService;

    Scanner sc = new Scanner(System.in);
    public InboundDeleteControllerImp(InboundDeleteService inboundDeleteService) {
        this.inboundDeleteService = inboundDeleteService;

    }

    @Override
    public void delete() {
        InboundDto inboundDto = new InboundDto();
        System.out.println("삭제할 입고번호 입력");
        String inbound_num = sc.nextLine();
        System.out.println("검사");
        if(inboundDto.getInbound_status() == 0){
            inboundDeleteService.Delete(inbound_num);
            System.out.println("삭제성공");
        }else {
            System.out.println("삭제실패");
        }

    }
}
