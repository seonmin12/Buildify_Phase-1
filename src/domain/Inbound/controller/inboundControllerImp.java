package domain.Inbound.controller;

import common.ValidCheck;

import java.util.Scanner;

public class inboundControllerImp implements inboundController {
    private ValidCheck validCheck;
    private InboundCheckController inboundCheckController;
    private InboundSearchController inboundSearchController;
    private InboundInsertController inboundInsertController;
    private InboundDeleteController inboundDeleteController;

    Scanner sc = new Scanner(System.in);

    @Override
    public void inboundAdminMain() {
        System.out.println("관리자 입고관리입니다.");
        System.out.println("1.입고요청확인 2.현황조회 3.나가기");
        int a = validCheck.inputNumRegex();
        sc.nextLine();
        switch (a){
            case 1: inboundCheckController.check();
            case 2:
                System.out.println("1.전체조회 2. 업체별조회 ");
                a = validCheck.inputNumRegex();
                switch (a){
                    case 1:inboundSearchController.SearchAll();
                    case 2:inboundSearchController.SearchOne();
                }
            case 3:
                System.out.println("나가기");
        }

    }

    @Override
    public void inboundUserMain() {
        System.out.println("회원 입고관리입니다.");
        System.out.println("1.입고요청 2.입고요청취소 3.현황조회");
        int a = validCheck.inputNumRegex();
        sc.nextLine();
        switch (a){
            case 1:inboundInsertController.insertrun();
            case 2:inboundDeleteController.delete();
        }


    }
}
