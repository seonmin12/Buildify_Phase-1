import common.ValidCheck;
import controller.WarehouseController;
import domain.DH_UserManagement.controller.SignUpController;
import domain.DH_UserManagement.controller.SignUpControllerImpl;
import domain.DH_UserManagement.repository.SignUpRepository;
import domain.DH_UserManagement.repository.SignUpRepositoryImpl;
import domain.DH_UserManagement.service.SignUpService;
import domain.DH_UserManagement.service.SignUpServiceImpl;
import domain.Inventory.controller.InventoryReadController;
import domain.Inventory.controller.InventoryReadControllerImp;
import domain.Inventory.controller.InventoryUpdateController;
import domain.Inventory.controller.InventoryUpdateCotrollerImp;
import domain.Inventory.repository.InventoryReadRepo;
import domain.Inventory.repository.InventoryReadRepoImp;
import domain.Inventory.repository.InventoryUpdateRepo;
import domain.Inventory.repository.InventoryUpdateRepoImp;
import domain.Inventory.service.InventoryReadService;
import domain.Inventory.service.InventoryReadServiceImp;
import domain.Inventory.service.InventoryUpdateService;
import domain.Inventory.service.InventoryUpdateServiceImp;
import dto.WarehouseDto;

public class Main {

    // 테스트용 메인
    public static void main(String[] args) {

        // inventory Test
//        InventoryReadRepo repo = new InventoryReadRepoImp();
//        InventoryReadService service = new InventoryReadServiceImp(repo);
//        ValidCheck validCheck = new ValidCheck();
//        InventoryReadController controller = new InventoryReadControllerImp(service,validCheck);
//        InventoryUpdateRepo updateRepo = new InventoryUpdateRepoImp();
//        InventoryUpdateService updateService = new InventoryUpdateServiceImp(updateRepo);
//        InventoryUpdateController updateController = new InventoryUpdateCotrollerImp(updateService,validCheck);
//        updateController.updateQuantity();


//        SignUpRepository repository = new SignUpRepositoryImpl();
//        SignUpService service1 = new SignUpServiceImpl(repository);
//        SignUpController controller1 = new SignUpControllerImpl(validCheck, service1);
//        controller1.signUp();



    }
}
