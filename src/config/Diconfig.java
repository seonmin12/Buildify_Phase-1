package config;

import common.ValidCheck;
import controller.WarehouseController;
import controller.WarehouseControllerImpl;
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

public class Diconfig {
    private final ValidCheck validCheck = new ValidCheck();

    private final LoginRepository loginRepository = new LoginRepositoryImpl();
    private final LoginService loginService = new LoginServiceImpl(loginRepository);

    private final UserManagementRepository userManagementRepository = new UserManagementRepositoryImpl();
    private final UserManagementService userManagementService = new UserManagementServiceImpl(userManagementRepository);

    private final InventoryReadRepo inventoryReadRepo = new InventoryReadRepoImp();
    private final InventoryReadService inventoryReadService = new InventoryReadServiceImp(inventoryReadRepo);
    private final InventoryReadController inventoryReadController = new InventoryReadControllerImp(inventoryReadService,validCheck);

    private final InventoryUpdateRepo inventoryUpdateRepo = new InventoryUpdateRepoImp();
    private final InventoryUpdateService inventoryUpdateService = new InventoryUpdateServiceImp(inventoryUpdateRepo);
    private final InventoryUpdateController inventoryUpdateController = new InventoryUpdateControllerImp(inventoryUpdateService,validCheck);

    private final InventoryDeleteRepo inventoryDeleteRepo = new InventoryDeleteRepoImp();
    private final InventoryDeleteService inventoryDeleteService = new InventoryDeleteServiceImp(inventoryDeleteRepo);
    private final InventoryDeleteController inventoryDeleteController = new InventoryDeleteControllerImp(inventoryDeleteService,validCheck);

    private final UserLoginRepository userLoginRepository = new UserLoginRepositoryImpl();
    private final UserLoginService userLoginService = new UserLoginServiceImpl(userLoginRepository);
    private final UserLoginController userLoginController = new UserLoginControllerImpl(validCheck,userLoginService);

    private final ProductRepository productRepository = new ProductRepositoryImpl();
    private final ProductService productService = new ProductServiceImpl(productRepository);
    private final ProductController productController = new ProductControllerImpl(validCheck,productService);

    private final SignUpRepository signUpRepository = new SignUpRepositoryImpl();
    private final SignUpService signUpService = new SignUpServiceImpl(signUpRepository);
    private final SignUpController signUpController = new SignUpControllerImpl(validCheck,signUpService);

    private final LoginController loginController = new LoginControllerImpl(validCheck,loginService);
    private final UserManagementController userManagementController = new UserManagementControllerImpl(userManagementService,validCheck);
    private final InventoryIntegratedController inventoryIntegratedController = new InventoryIntegratedControllerImp(inventoryReadController,inventoryUpdateController,inventoryDeleteController,validCheck);
    private final UserIntegratedController userIntegratedController = new UserIntegratedControllerImpl(userLoginController,productController,signUpController);
    private final AdminController adminController = new AdminController(validCheck, loginController, userManagementController);

    public WarehouseController warehouseController(){
        return new WarehouseControllerImpl(loginController,
                userManagementController,
                validCheck,
                inventoryIntegratedController,
                userIntegratedController,
                adminController);
    }
}
