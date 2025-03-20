package domain.Inbound.controller;

import common.ValidCheck;
import domain.Inbound.service.InboundCheckService;
import domain.Inbound.service.InboundSearchService;
import dto.ClientUpdateDto;
import dto.InboundDto;
import dto.UserDto;

import java.util.List;

public class InboundCheckControllerImp implements InboundCheckController {
    private final InboundCheckService inboundCheckService ;
    private final InboundSearchService inboundSearchService;
    private final ValidCheck validCheck;


    public InboundCheckControllerImp(InboundCheckService inboundCheckService, InboundSearchService inboundSearchService, ValidCheck validCheck) {
        this.inboundCheckService = inboundCheckService;
        this.inboundSearchService = inboundSearchService;
        this.validCheck = validCheck;
    }

    List<InboundDto> dto;
    List<UserDto> userDto;

    /**
     * 관리자 입고확인 (승인/반려)
     */
    @Override
    public void check( ) {
        String status;
        System.out.println("1.전체확인 2.업체별확인 3.개별확인 4.나가기");
        int a = validCheck.inputNumRegex();
        switch (a) {
            case 1:
                dto = inboundCheckService.allCheckRead();
                for (InboundDto inboundDto : dto) {
                    if (inboundDto.getInbound_status() == 0){
                         status= "대기";
                    }else if (inboundDto.getInbound_status() == 1){
                        status = "승인";
                    }else status = "반려";
                    System.out.printf("입고번호: %s | 상품ID: %s | 고객ID: %s | 수량: %d | 상태: %s | 요청일: %s | 창고ID: %s\n",
                            inboundDto.getInbound_number(), inboundDto.getProd_id(), inboundDto.getClient_id(), inboundDto.getQuantity()
                            , status, inboundDto.getReq_inbound_day(), inboundDto.getWare_id());
                }
                if (dto == null || dto.isEmpty()) {
                    System.out.println("전체입고확인 정보가 없습니다.");
                    return;
                }


                System.out.println("1.승인 2.반려 3.나가기");
                a = validCheck.inputNumRegex();
                switch (a) {
                    case 1:
                        inboundCheckService.allCheckUpdate();


                        break;
                    case 2:
                        inboundCheckService.allCheckReturn();
                        break;
                    case 3:
                        break;

                }
                break;


            case 2:
                  List<ClientUpdateDto> clientUpdateDto = inboundSearchService.clientupdatesearch();

                if (clientUpdateDto == null || clientUpdateDto.isEmpty()) {
                    System.out.println("클라이언트 입고 정보가 없습니다.");
                    return;
                }

                for (ClientUpdateDto clientUpdateDto1 : clientUpdateDto) {
                    System.out.println("업체명: " + clientUpdateDto1.getClient_id() + "  ("+clientUpdateDto1.getUser_id()+")");
                }
                String ci;
                while (true) {
                    System.out.print("업체 이름 입력: ");
                    ci = validCheck.inputAnyString();
                    dto = inboundCheckService.clientCheckRead(ci);

                    if (dto.isEmpty()) {
                        System.out.println("잘못입력하였습니다.");
                    } else {
                        break;
                    }
                }

                for (InboundDto inboundDto : dto) {
                    if (inboundDto.getInbound_status() == 0){
                        status= "대기";
                    }else if (inboundDto.getInbound_status() == 1){
                        status = "승인";
                    }else status = "반려";
                    System.out.printf("입고번호: %s | 상품ID: %s | 고객ID: %s | 수량: %d | 상태: %s | 요청일: %s | 창고ID: %s\n",
                            inboundDto.getInbound_number(), inboundDto.getProd_id(), inboundDto.getClient_id(), inboundDto.getQuantity()
                            , status, inboundDto.getReq_inbound_day(), inboundDto.getWare_id());
                }

                System.out.println("1.승인 2.반려 3.나가기");
                int b = validCheck.inputNumRegex();

                switch (b) {
                    case 1:
                        inboundCheckService.clientCheckUpdate(ci);
                        check();
                        break;
                    case 2:
                        inboundCheckService.clientCheckReturn(ci);
                        check();
                        break;
                    case 3:
                        check();
                        break;
                }
                break;


        case 3:

        dto = inboundCheckService.allCheckRead();
        for (InboundDto inboundDto : dto) {
            if (inboundDto.getInbound_status() == 0){
                status= "대기";
            }else if (inboundDto.getInbound_status() == 1){
                status = "승인";
            }else status = "반려";
            System.out.printf("입고번호: %s | 상품ID: %s | 고객ID: %s | 수량: %d | 상태: %s | 요청일: %s | 창고ID: %s\n",
                    inboundDto.getInbound_number(), inboundDto.getProd_id(), inboundDto.getClient_id(), inboundDto.getQuantity()
                    , status, inboundDto.getReq_inbound_day(), inboundDto.getWare_id());
        }
            if (dto == null || dto.isEmpty()) {
                System.out.println("입고확인 정보가 없습니다.");
                return;
            }
        String in = "";
        boolean isValid = false;
        while (!isValid) {
            System.out.print("입고번호 입력: ");
            in = validCheck.inputAnyString();

            for (InboundDto inboundDto : dto) {
                if (inboundDto.getInbound_number().equals(in)) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                System.out.println("잘못입력함");
            }
        }

        System.out.println("1.승인 2.반려");
        int c = validCheck.inputNumRegex();
        switch (c) {
            case 1:
                inboundCheckService.numCheckUpdate(in);
                break;
            case 2:
                inboundCheckService.numCheckReturn(in);
                break;
        }
            break;


            case 4: return;

            default:
                System.out.println("잘못입력하였습니다.");



        }

    }
}









