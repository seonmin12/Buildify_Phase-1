package controller;

import common.ValidCheck;
import domain.DH_UserManagement.controller.*;
import domain.DH_UserManagement.repository.*;
import domain.DH_UserManagement.service.*;
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
import dto.UserDto;

import static common.ErrorCode.*;
import static common.Text.*;

// 전체 통합 메인 컨트롤러
public class WarehouseControllerImpl implements WarehouseController{
    private final LoginController loginController;
    private final UserManagementController userManagementController;
    private final UserLoginController userLoginController;
    private final SignUpController signUpController;
    private final ReqProdRegitController  reqProdRegitController;
    private final ValidCheck validCheck;

    public WarehouseControllerImpl(ReqProdRegitController reqProdRegitController, UserLoginController userLoginController, SignUpController signUpController, LoginController loginController, UserManagementController userManagementController, ValidCheck validCheck) {
        this.loginController = loginController;
        this.userManagementController = userManagementController;
        this.userLoginController = userLoginController;
        this.signUpController = signUpController;
        this.reqProdRegitController = reqProdRegitController;
        this.validCheck = validCheck;
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
            }
            else {
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
        boolean isLogin = userLoginController.login();

        if(!isLogin) {
            start();
            return;
        }

        UserDto userDto = userLoginController.getUserInfo();


        while (true){
            System.out.println("1. 상품 정보 등록 2. 재고관리 3. 입고관리 4. 출고관리 5. 나의 정보 변경");
            int menu = validCheck.inputNumRegex();

            switch (menu) {
                case 1:
                    System.out.println("상품 정보 등록 기능 동작");
                    boolean result = reqProdRegitController.requestProdcutRegist();
                    if(result){
                        System.out.println("상품 정보 등록되었습니다.");
                    }
                    break;
                case 2:
                    System.out.println("재고 관리 기능 동작");
                    break;
                case 3:
                    System.out.println("입고 관리 기능 동작");
                    break;
                case 4 :
                    System.out.println("출고 관리 기능 동작");
                    break;
                case 5:
                    System.out.println("나의 정보 변경 동작");
                    break;
                default:
                    System.out.println("잘못 입력 하였습니다.");
                    break;
            }
        }

        // 1. 상품 등록 요청
        // 2.
        //menu
        //만약에 로그아웃 해서 최상위 메뉴로 간다면?
        //start();
    }

    @Override
    public void signUp() {
        signUpController.signUp();
        start();
    }

    @Override
    public void adminUserManagement(AdminDto adminDto) {
        System.out.println(ADMIN_USER_MANAGEMENT_MENU.getText());
        int choice = validCheck.inputNumRegex();
        switch (choice){
            case 1:
                userManagementController.pendingApprovalUsers(adminDto);
                System.out.println(ADMIN_MENU_CHOICE.getText());
                choice = validCheck.inputNumRegex();
                switch (choice) {
                    case 1 -> userManagementController.approveUser(adminDto);
                    case 2 -> {
                        return;
                    }
                }
                break;
            case 2:
                System.out.println(ADMIN_USER_SERACH.getText());
                choice = validCheck.inputNumRegex();
                switch (choice) {
                    case 1 -> userManagementController.listAllUsers(adminDto);
                    case 2 -> userManagementController.searchUser(adminDto);
                }
                break;
            case 3:
                System.out.println(USER_INFO_CHANGER.getText());
                userManagementController.updateUser(adminDto);
                break;
            case 4:
                System.out.println("1. 내 정보 조회 2. 내 정보 수정");
                choice = validCheck.inputNumRegex();
                switch (choice) {
                    case 1 -> userManagementController.searchMyInfo(adminDto);
                    case 2 -> userManagementController.updateSelfAdmin(adminDto);
                    default -> System.out.println(ERROR_INPUT.getText());
                }

                break;
            case 5:
                System.out.println(LOCAL_ADMIN_MENU.getText());
                choice = validCheck.inputNumRegex();
                switch (choice){
                    case 1 -> userManagementController.listAllLocalAdmin(adminDto);
                    case 2 -> {
                        adminDto = loginController.getAdminLoginStatus();
                        userManagementController.updateAdmin(adminDto);
                    }
                    default -> System.out.println(ERROR_INPUT.getText());
                }
                break;
            default:
                System.out.println(ERROR_INPUT.getText());
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

        UserLoginRepository userLoginRepository = new UserLoginRepositoryImpl();
        UserLoginService userLoginService = new UserLoginServiceImpl(userLoginRepository);
        UserLoginController userLoginController = new UserLoginControllerImpl(validCheck1,userLoginService);

        SignUpRepository signUpRepository = new SignUpRepositoryImpl();
        SignUpService signUpService = new SignUpServiceImpl(signUpRepository);
        SignUpController signUpController = new SignUpControllerImpl(validCheck1, signUpService);

        ReqProdRegitRepository reqProdRegitRepository = new ReqProdRegitRepositoryImpl();
        ReqProdRegitService reqProdRegitService = new ReqProdRegitServiceImpl(reqProdRegitRepository);
        ReqProdRegitController reqProdRegitController = new ReqProdRegitControllerImpl(validCheck1, reqProdRegitService);

        WarehouseController warehouseController = new WarehouseControllerImpl(reqProdRegitController, userLoginController, signUpController, loginController1, userManagementController1,validCheck1);
        warehouseController.start();
    }
}
