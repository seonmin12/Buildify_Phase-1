package controller;

import common.ValidCheck;
import domain.UserManagement.controller.LoginController;
import dto.AdminDto;

// 전체 통합 메인 컨트롤러
public class WarehouseControllerImpl implements WarehouseController{
    private final LoginController loginController;
    private final ValidCheck validCheck;

    public WarehouseControllerImpl(LoginController loginController, ValidCheck validCheck) {
        this.loginController = loginController;
        this.validCheck = validCheck;
    }

    @Override
    public void start() {
        System.out.println("+--------------------------------------+");
        System.out.println("|      Warehouse Management System     |");
        System.out.println("|                                      |");
        System.out.println("|           Buildify System            |");
        System.out.println("|                                      |");
        System.out.println("+--------------------------------------+");

        System.out.println("1.관리자 로그인");
        System.out.println("2.회원 로그인");
        System.out.println("3.프로그램 종료");
        System.out.printf("선택 : ");
        int choice = validCheck.inputNumRegex();

        switch (choice){
            case 1 -> adminStart();
            case 2 -> userStart();
            case 3 -> {
                System.out.println("종료");
                System.exit(0);
            }
            default -> {
                System.out.println("올바른 숫자를 입력해주세요");
                start();
            }
        }
    }

    @Override
    public void adminStart() {
        System.out.println("로그인을 시작합니다.");
        loginController.login();
        AdminDto adminDto = loginController.getAdminLoginStatus();

        while (true){
            if (adminDto == null){
                loginController.login();
                adminDto = loginController.getAdminLoginStatus();
            }
            else {
                break;
            }
        }

        while (true) {
            System.out.println("1.회원관리 2.입고관리 3.출고관리 4.재고관리 5.로그아웃");
            System.out.println("메뉴선택:");
            int choice = validCheck.inputNumRegex();
            switch (choice) {
                case 1 -> System.out.println("1");//usermanagementController.userstart(adminDto); 회원정보 메뉴 실행
                case 2 -> System.out.println("2");//inboundController.inboundstart(adminDto); 입고관리 메뉴 실행
                case 3 -> System.out.println("3");//outboundController.outboundstart(adminDto); 출고관리 메뉴 실행
                case 4 -> System.out.println("4");//inventoryController.inventorystart(adminDto); 재고관리 정보 실행
                case 5 -> {
                    System.out.println("로그아웃");
                    start();
                }
                default -> {
                    System.out.println("올바른 번호를 입력하세요");
                    break;
                }
            }
        }
    }

    @Override
    public void userStart() {

    }
}
