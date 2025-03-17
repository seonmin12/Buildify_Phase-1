package domain.Inbound.controller;

import common.ValidCheck;
import domain.Inbound.service.InboundCheckService;
import domain.Inbound.service.InboundCheckServiceImp;

import java.util.Scanner;

public class InboundCheckControllerImp implements InboundCheckController {
    InboundCheckService inboundCheckService = new InboundCheckServiceImp();
    Scanner sc = new Scanner(System.in);
    ValidCheck validCheck = new ValidCheck();


    @Override
    public void check() {
        System.out.println("1.승인 2.반려 3.나가기");
        System.out.print("선택: ");
        int a = validCheck.inputNumRegex();

        switch (a) {

            case 1:
                System.out.println("1.전체승인 2.개별승인");
                a = validCheck.inputNumRegex();
                switch (a) {
                    case 1 -> inboundCheckService.allCheckOk(a);
                    case 2:
                        System.out.println("1.클라이언트별 2.상품별");
                        a = validCheck.inputNumRegex();
                        switch (a) {
                            case 1 -> inboundCheckService.checkClient(a) ;
//                                System.out.println("조회할 클라이언트 입력");




//                            case 2 -> inboundCheckService.checkProd(a); {
//                            }
                        }
                }


        }


    }
}

