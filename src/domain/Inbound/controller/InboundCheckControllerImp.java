package domain.Inbound.controller;

import common.ErrorCode;
import common.ValidCheck;
import config.DBConnection;
import controller.WarehouseController;
import controller.WarehouseControllerImpl;
import domain.Inbound.service.InboundCheckService;
import domain.Inbound.service.InboundCheckServiceImp;
import domain.Inbound.service.InboundSearchService;
import dto.InboundDto;
import exception.InboundException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class InboundCheckControllerImp implements InboundCheckController {
    private final InboundCheckService inboundCheckService ;
    private final InboundSearchService inboundSearchService;

    Scanner sc = new Scanner(System.in);
    private final ValidCheck validCheck;
    Connection connection = DBConnection.getConnection();

    public InboundCheckControllerImp(InboundCheckService inboundCheckService, InboundSearchService inboundSearchService, ValidCheck validCheck) {
        this.inboundCheckService = inboundCheckService;
        this.inboundSearchService = inboundSearchService;
        this.validCheck = validCheck;
    }


    List<InboundDto> dto;

    @Override
    public void check() {

        System.out.println("1.전체확인 2.업체별확인 3.개별확인 4.나가기");
        int a = validCheck.inputNumRegex();
        switch (a) {
            case 1:
                dto = inboundCheckService.allCheckRead();
                for (InboundDto inboundDto : dto) {
                    System.out.printf("입고번호: %s | 상품ID: %s | 고객ID: %s | 수량: %d | 상태: %d | 요청일: %s | 창고ID: %s\n",
                            inboundDto.getInbound_number(), inboundDto.getProd_id(), inboundDto.getClient_id(), inboundDto.getQuantity()
                            , inboundDto.getInbound_status(), inboundDto.getReq_inbound_day(), inboundDto.getWare_id());
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
                List<InboundDto> inboundDtoList = inboundSearchService.clientupdatesearch();

                if(inboundDtoList.isEmpty()){
                    System.out.println("승인이 필요한 업체가 없습니다.");
                    return;
                }
                for (InboundDto inboundDto : inboundDtoList) {
                    System.out.println("업체명: " + inboundDto.getClient_id());
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
                    System.out.printf("입고번호: %s | 상품ID: %s | 고객ID: %s | 수량: %d | 상태: %d | 요청일: %s | 창고ID: %s\n",
                            inboundDto.getInbound_number(), inboundDto.getProd_id(), inboundDto.getClient_id(), inboundDto.getQuantity()
                            , inboundDto.getInbound_status(), inboundDto.getReq_inbound_day(), inboundDto.getWare_id());
                }

                System.out.println("1.승인 2.반려 3.나가기");
                int b = validCheck.inputNumRegex();
//
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
                    System.out.printf("입고번호: %s | 상품ID: %s | 고객ID: %s | 수량: %d | 상태: %d | 요청일: %s | 창고ID: %s\n",
                            inboundDto.getInbound_number(), inboundDto.getProd_id(), inboundDto.getClient_id(), inboundDto.getQuantity()
                            , inboundDto.getInbound_status(), inboundDto.getReq_inbound_day(), inboundDto.getWare_id());
                }
                String in = "" ;
                boolean isValid = false;
                while (!isValid) {
                    System.out.print("입고번호 입력: ");
                    in = validCheck.inputAnyString();

                    for (InboundDto inboundDto : dto) {
                        if(inboundDto.getInbound_number().equals(in)){
                            isValid = true;
                            break;
                        }
                    }
                    if(!isValid){
                        System.out.println("잘못입력함");
                    }
                }

                System.out.println("1.승인 2.반려");
                int c = validCheck.inputNumRegex();
                switch (c) {
                    case 1:
                        inboundCheckService.numCheckUpdate(in); break;
                    case 2:
                        inboundCheckService.numCheckReturn(in); break;
                }break;
            case 4: break;

        }

    }
}









