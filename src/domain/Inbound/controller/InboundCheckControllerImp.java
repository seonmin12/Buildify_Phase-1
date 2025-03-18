package domain.Inbound.controller;

import common.ErrorCode;
import common.ValidCheck;
import config.DBConnection;
import domain.Inbound.service.InboundCheckService;
import domain.Inbound.service.InboundCheckServiceImp;
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
    Scanner sc = new Scanner(System.in);
    ValidCheck validCheck = new ValidCheck();
    Connection connection = DBConnection.getConnection();

    public InboundCheckControllerImp(InboundCheckService inboundCheckService) {
        this.inboundCheckService = inboundCheckService;
    }


    List<InboundDto> dto;

    @Override
    public void check() {
        System.out.println("1.요청건확인 2.나가기");
        System.out.print("선택: ");
        int a = validCheck.inputNumRegex();

        switch (a) {
            case 1:

                System.out.println("1.전체확인 2.업체별확인 3.상품별확인 4.개별확인");
                a = validCheck.inputNumRegex();
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
                        }
                        break;


                    case 2:

                        System.out.print("업체 이름 입력: ");
                        String ci = validCheck.inputAnyString();
                        dto = inboundCheckService.prodCheckRead(ci);
                        for (InboundDto inboundDto : dto) {
                            System.out.printf("입고번호: %s | 상품ID: %s | 고객ID: %s | 수량: %d | 상태: %d | 요청일: %s | 창고ID: %s\n",
                                    inboundDto.getInbound_number(), inboundDto.getProd_id(), inboundDto.getClient_id(), inboundDto.getQuantity()
                                    , inboundDto.getInbound_status(), inboundDto.getReq_inbound_day(), inboundDto.getWare_id());
                            System.out.println("1.승인 2.반려 3.나가기");
                            int b = validCheck.inputNumRegex();
//
                            switch (b){
                                case 1: inboundCheckService.clientCheckUpdate(ci);
                            }
                        }

//
//                    case 1:
//                        System.out.print("조회할 클라이언트 입력: ");
//                        String client = validCheck.inputAnyString();
//                        inboundCheckService.(client);


//                    case 2:
//                        System.out.print("조회할 상품명 입력: ");
//                        String prod = validCheck.inputAnyString();
//                        inboundCheckService.checkProd(prod);
//
//                    default:

                }}
                }
//            case 2 : //반려
//
//            case 3 : //나가기


        }





