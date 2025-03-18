package controller;

import common.ValidCheck;
import domain.AccountManagement.Admin.controller.AdminController;
import domain.AccountManagement.Admin.controller.LoginController;
import domain.AccountManagement.Admin.controller.UserManagementController;
import domain.AccountManagement.User.controller.UserController;
import domain.Inbound.controller.InboundController;
import domain.Inventory.controller.*;
import domain.Inventory.repository.*;
import domain.Inventory.service.*;
import dto.AdminDto;
import dto.UserDto;

import static common.ErrorCode.*;
import static common.Text.*;

// 전체 통합 메인 컨트롤러
public class WarehouseControllerImpl implements WarehouseController{

    private final LoginController loginController;
    private final UserManagementController userManagementController;
    private final ValidCheck validCheck;
    private final InventoryIntegratedController inventoryIntegratedController; // feature 브랜치 변경사항
    private final UserController userController;
    private final AdminController adminController;
    private final InboundController inboundController;

    // === 통합된 생성자 ===
    // 두 브랜치 변경사항 합쳐서, reqProdRegitController + inventoryIntegratedController 모두 주입
    public WarehouseControllerImpl(
            LoginController loginController,
            UserManagementController userManagementController,
            ValidCheck validCheck,
            InventoryIntegratedController inventoryIntegratedController,
            UserController userController, AdminController adminController, InboundController inboundController
    ) {
        this.loginController = loginController;
        this.userManagementController = userManagementController;
        this.validCheck = validCheck;
        this.inventoryIntegratedController = inventoryIntegratedController;
        this.userController = userController;
        this.adminController = adminController;
        this.inboundController = inboundController;
    }

    @Override
    public void start() {
        System.out.println(PROGRAM_START_1.getText());
        System.out.println(PROGRAM_START_2.getText());
        System.out.println(PROGRAM_START_3.getText());
        System.out.println(PROGRAM_START_4.getText());
        System.out.println(PROGRAM_START_5.getText());
        System.out.println(PROGRAM_START_6.getText());

        System.out.println(LOGIN_MENU.getText());
        System.out.printf(INPUT_CHOICE.getText());
        int choice = validCheck.inputNumRegex();

        switch (choice){
            case 1 -> adminStart();
            case 2 -> signUp();
            case 3 -> userStart();
            case 4 -> {
                System.out.println(EXIT.getText());
                System.exit(0);
            }
            default -> {
                System.out.println(ERROR_INPUT.getText());
                start();
            }
        }
    }

    @Override
    public void adminStart() {
        System.out.println(START_LOGIN.getText());
        loginController.login();
        AdminDto adminDto = loginController.getAdminLoginStatus();

        while (true){
            if (adminDto == null){
                loginController.login();
                adminDto = loginController.getAdminLoginStatus();
            } else {
                break;
            }
        }

        while (true) {
            System.out.println(ADMIN_MENU.getText());
            System.out.println(INPUT_CHOICE.getText());
            int choice = validCheck.inputNumRegex();
            switch (choice) {
                case 1 -> adminUserManagement(adminDto);
                case 2 -> adminInboundStart(adminDto);
                case 3 -> adminOutboundStart(adminDto);
                case 4 -> adminInventoryStart(adminDto);
                case 5 -> {
                    System.out.println(LOGOUT.getText());
                    start();
                }
                default -> {
                    System.out.println(ERROR_INPUT.getText());
                    break;
                }
            }
        }
    }

    @Override
    public void userStart() {
        boolean isLogin = userController.userLogin();

        if(!isLogin) {
            start();
            return;
        }

        UserDto userDto = userController.getUserInfo();

        while (true){
            System.out.println("1. 상품 정보 등록 2. 상품 정보 출력 3. 재고관리 4. 입고관리 5. 출고관리 6. 나의 정보 변경 7. 로그아웃");
            int menu = validCheck.inputNumRegex();

            switch (menu) {
                case 1:
                    System.out.println("상품 정보 등록 기능 동작");
                    boolean result = userController.requestProductRegist();
                    if(result){
                        System.out.println("상품 정보 등록되었습니다.");
                    }
                    break;
                case 2:
                    userController.getAllProducts();
                    break;
                case 3:
                    System.out.println("재고 관리 기능 동작");
                    break;
                case 4 :
                    System.out.println("입고 관리 기능 동작");
                    inboundController.inboundUserMain(userDto);
                    break;
                case 5:
                    System.out.println("출고 관리 기능 동작");
                    break;
                case 6:
                    userController.updateUserInfo();
                    break;
                case 7:
                    userController.userLogout();
                    start();
                    break;
                default:
                    System.out.println("잘못 입력 하였습니다.");
                    break;
            }
        }
    }

    @Override
    public void signUp() {
        userController.userSignUp();
        start();
    }

    @Override
    public void adminUserManagement(AdminDto adminDto) {
        System.out.println("현재 로그인 관리자 : " + adminDto.getAdminName());
        adminController.adminUserManagement(adminDto);
    }

    @Override
    public void adminInboundStart(AdminDto adminDto) {
        System.out.println("현재 로그인 관리자 : " + adminDto.getAdminName());
        inboundController.inboundAdminMain();
    }

    @Override
    public void adminOutboundStart(AdminDto adminDto) {
        System.out.println("현재 로그인 관리자 : " + adminDto.getAdminName());
    }

    @Override
    public void adminInventoryStart(AdminDto adminDto) {
        System.out.println("현재 로그인 관리자 : " + adminDto.getAdminName());
        // 관리자 재고 기능 → 통합 컨트롤러 실행
        inventoryIntegratedController.inventoryRunForAdmin();
    }

}

