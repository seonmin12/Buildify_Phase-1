package config;

import common.ValidCheck;
import controller.WarehouseController;
import controller.WarehouseControllerImpl;
import domain.AccountManagement.User.controller.*;
import domain.AccountManagement.User.service.*;
import domain.AccountManagement.User.repository.*;
import domain.Inventory.controller.*;
import domain.Inventory.repository.*;
import domain.Inventory.service.*;
import domain.AccountManagement.Admin.controller.*;
import domain.AccountManagement.Admin.service.*;
import domain.AccountManagement.Admin.repository.*;

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
    private final InventoryIntegratedController inventoryIntegratedController = new InventoryIntegratedController(inventoryReadController,inventoryUpdateController,inventoryDeleteController,validCheck);
    private final UserController userController = new UserController(userLoginController,productController,signUpController);
    private final AdminController adminController = new AdminController(validCheck, loginController, userManagementController);

    public WarehouseController warehouseController(){
        return new WarehouseControllerImpl(loginController,
                userManagementController,
                validCheck,
                inventoryIntegratedController,
                userController,
                adminController);
    }
}
