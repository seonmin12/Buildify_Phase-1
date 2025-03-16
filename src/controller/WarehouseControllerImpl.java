package controller;

import common.ValidCheck;
import domain.UserManagement.controller.LoginController;
import domain.UserManagement.controller.LoginControllerImpl;
import domain.UserManagement.controller.UserManagementController;
import domain.UserManagement.controller.UserManagementControllerImpl;
import domain.UserManagement.repository.LoginRepository;
import domain.UserManagement.repository.LoginRepositoryImpl;
import domain.UserManagement.repository.UserManagementRepository;
import domain.UserManagement.repository.UserManagementRepositoryImpl;
import domain.UserManagement.service.LoginService;
import domain.UserManagement.service.LoginServiceImpl;
import domain.UserManagement.service.UserManagementService;
import domain.UserManagement.service.UserManagementServiceImpl;
import dto.AdminDto;

// 전체 통합 메인 컨트롤러
public class WarehouseControllerImpl implements WarehouseController{
    private final LoginController loginController;
    private final UserManagementController userManagementController;
    private final ValidCheck validCheck;

    public WarehouseControllerImpl(LoginController loginController, UserManagementController userManagementController, ValidCheck validCheck) {
        this.loginController = loginController;
        this.userManagementController = userManagementController;
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
                case 1 -> adminUserManagement(adminDto);
                case 2 -> adminInboundStart(adminDto);
                case 3 -> adminOutboundStart(adminDto);
                case 4 -> adminInventoryStart(adminDto);
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

    @Override
    public void adminUserManagement(AdminDto adminDto) {
        System.out.println("1.승인대기회원 2.회원조회 3.회원정보수정 4.내 정보 수정 5.관리자 수정 및 조회(총관리자만가능)");
        int choice = validCheck.inputNumRegex();
        switch (choice){
            case 1:
                userManagementController.pendingApprovalUsers(adminDto);
                System.out.println("1.승인 2.이전메뉴");
                choice = validCheck.inputNumRegex();
                switch (choice) {
                    case 1 -> userManagementController.approveUser(adminDto);
                    case 2 -> {
                        return;
                    }
                }
                break;
            case 2:
                System.out.println("1.전체 회원 조회 2.회원 검색");
                choice = validCheck.inputNumRegex();
                switch (choice) {
                    case 1 -> userManagementController.listAllUsers(adminDto);
                    case 2 -> userManagementController.searchUser(adminDto);
                }
                break;
            case 3:
                System.out.println("회원정보수정");
                userManagementController.updateUser(adminDto);
                break;
            case 4:
                userManagementController.updateSelfAdmin(adminDto);
                break;
            case 5:
                System.out.println("1.창고관리자 조회 2.창고관리자 수정");
                choice = validCheck.inputNumRegex();
                switch (choice){
                    case 1 -> userManagementController.listAllLocalAdmin(adminDto);
                    case 2 -> userManagementController.updateAdmin(adminDto);
                    default -> System.out.println("올바른 메뉴를 선택하세요.");
                }
                break;
            default:
                System.out.println("올바른 메뉴를 선택하세요.");
                return;
        }

    }

    @Override
    public void adminInboundStart(AdminDto adminDto) {
        System.out.println("현재 로그인 관리자 : " + adminDto.getAdminName());
        System.out.println("입고관리 기능 추가 예정");
    }

    @Override
    public void adminOutboundStart(AdminDto adminDto) {
        System.out.println("현재 로그인 관리자 : " + adminDto.getAdminName());
        System.out.println("출고관리 기능 추가 예정");
    }

    @Override
    public void adminInventoryStart(AdminDto adminDto) {
        System.out.println("현재 로그인 관리자 : " + adminDto.getAdminName());
        System.out.println("재고관리 기능 추가 예정");
    }

    //관리자 테스트 코드
    public static void main(String[] args) {
        ValidCheck validCheck1 = new ValidCheck();
        LoginRepository loginRepository = new LoginRepositoryImpl();
        LoginService loginService = new LoginServiceImpl(loginRepository);
        LoginController loginController1 =new LoginControllerImpl(validCheck1,loginService);
        UserManagementRepository userManagementRepository = new UserManagementRepositoryImpl();
        UserManagementService userManagementService = new UserManagementServiceImpl(userManagementRepository);
        UserManagementController userManagementController1 = new UserManagementControllerImpl(userManagementService,validCheck1);

        WarehouseController warehouseController = new WarehouseControllerImpl(loginController1,userManagementController1,validCheck1);
        warehouseController.start();
    }
}
