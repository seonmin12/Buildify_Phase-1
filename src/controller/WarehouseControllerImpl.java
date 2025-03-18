package controller;

import common.ValidCheck;
import domain.DH_UserManagement.controller.*;
import domain.DH_UserManagement.repository.*;
import domain.DH_UserManagement.service.*;
import domain.Inventory.controller.*;
import domain.Inventory.repository.*;
import domain.Inventory.service.*;
import domain.UserManagement.controller.*;
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
    private final ValidCheck validCheck;
    private final InventoryIntegratedController inventoryIntegratedController; // feature 브랜치 변경사항
    private final UserIntegratedController userIntegratedController;
    private final AdminController adminController;

    // === 통합된 생성자 ===
    // 두 브랜치 변경사항 합쳐서, reqProdRegitController + inventoryIntegratedController 모두 주입
    public WarehouseControllerImpl(
            LoginController loginController,
            UserManagementController userManagementController,
            ValidCheck validCheck,
            InventoryIntegratedController inventoryIntegratedController,
            UserIntegratedController userIntegratedController, AdminController adminController
    ) {
        this.loginController = loginController;
        this.userManagementController = userManagementController;
        this.validCheck = validCheck;
        this.inventoryIntegratedController = inventoryIntegratedController;
        this.userIntegratedController = userIntegratedController;
        this.adminController = adminController;
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
        boolean isLogin = userIntegratedController.userLogin();

        if(!isLogin) {
            start();
            return;
        }

        UserDto userDto = userIntegratedController.getUserInfo();

        while (true){
            System.out.println("1. 상품 정보 등록 2. 상품 정보 출력 3. 재고관리 4. 입고관리 5. 출고관리 6. 나의 정보 변경 7. 로그아웃");
            int menu = validCheck.inputNumRegex();

            switch (menu) {
                case 1:
                    System.out.println("상품 정보 등록 기능 동작");
                    boolean result = userIntegratedController.requestProductRegist();
                    if(result){
                        System.out.println("상품 정보 등록되었습니다.");
                    }
                    break;
                case 2:
                    userIntegratedController.getAllProducts();
                    break;
                case 3:
                    System.out.println("재고 관리 기능 동작");
                    break;
                case 4 :
                    System.out.println("입고 관리 기능 동작");
                    break;
                case 5:
                    System.out.println("출고 관리 기능 동작");
                    break;
                case 6:
                    System.out.println("나의 정보 변경 동작");
                    break;
                case 7:
                    userIntegratedController.userLogout();
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
        userIntegratedController.userSignUp();
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
        System.out.println("입고관리 기능 추가 예정");
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

    // 관리자 테스트 코드
    public static void main(String[] args) {
        ValidCheck validCheck1 = new ValidCheck();

        // Login
        LoginRepository loginRepository = new LoginRepositoryImpl();
        LoginService loginService = new LoginServiceImpl(loginRepository);
        LoginController loginController1 = new LoginControllerImpl(validCheck1, loginService);

        UserManagementRepository userManagementRepository = new UserManagementRepositoryImpl();
        UserManagementService userManagementService = new UserManagementServiceImpl(userManagementRepository);
        UserManagementController userManagementController1 = new UserManagementControllerImpl(userManagementService,validCheck1);

        // User Management
        UserManagementRepository userManagementRepository = new UserManagementRepositoryImpl();
        UserManagementService userManagementService = new UserManagementServiceImpl(userManagementRepository);
        UserManagementController userManagementController1 = new UserManagementControllerImpl(userManagementService, validCheck1);

        // Read (feature 브랜치에서 온 부분)
        InventoryReadRepo readRepo = new InventoryReadRepoImp();
        InventoryReadService readService = new InventoryReadServiceImp(readRepo);
        InventoryReadController readController = new InventoryReadControllerImp(readService, validCheck1);

        // Update
        InventoryUpdateRepo updateRepo = new InventoryUpdateRepoImp();
        InventoryUpdateService updateService = new InventoryUpdateServiceImp(updateRepo);
        InventoryUpdateController updateController = new InventoryUpdateControllerImp(updateService, validCheck1);

        // Delete
        InventoryDeleteRepo deleteRepo = new InventoryDeleteRepoImp();
        InventoryDeleteService deleteService = new InventoryDeleteServiceImp(deleteRepo);
        // 주의! 클래스 이름 오타 없게
        InventoryDeleteController deleteController = new InventoryDeleteControllerImp(deleteService, validCheck1);

        UserLoginRepository userLoginRepository = new UserLoginRepositoryImpl();
        UserLoginService userLoginService = new UserLoginServiceImpl(userLoginRepository);
        UserLoginController userLoginController = new UserLoginControllerImpl(validCheck1,userLoginService);

        ProductRepository productRepository = new ProductRepositoryImpl();
        ProductService productService = new ProductServiceImpl(productRepository);
        ProductController productController = new ProductControllerImpl(validCheck1, productService);

        SignUpRepository signUpRepository = new SignUpRepositoryImpl();
        SignUpService signUpService = new SignUpServiceImpl(signUpRepository);
        SignUpController signUpController = new SignUpControllerImpl(validCheck1, signUpService);

        UserIntegratedController userIntegratedController = new UserIntegratedControllerImpl(
                userLoginController,
                productController,
                signUpController
        );

        // 통합 재고 컨트롤러
        InventoryIntegratedController inventoryIntegratedController = new InventoryIntegratedControllerImp(
                readController,
                updateController,
                deleteController,
                validCheck1
        );

        AdminController adminController1 = new AdminController(validCheck1,loginController1,userManagementController1);


        // 최종 통합 컨트롤러
        WarehouseController warehouseController = new WarehouseControllerImpl(
                loginController1,
                userManagementController1,
                validCheck1,
                inventoryIntegratedController,  // feature
                userIntegratedController,adminController1
        );

        warehouseController.start();
    }
}

