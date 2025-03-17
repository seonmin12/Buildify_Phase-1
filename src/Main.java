import common.ValidCheck;
import controller.WarehouseController;
import domain.DH_UserManagement.controller.SignUpController;
import domain.DH_UserManagement.controller.SignUpControllerImpl;
import domain.DH_UserManagement.repository.SignUpRepository;
import domain.DH_UserManagement.repository.SignUpRepositoryImpl;
import domain.DH_UserManagement.service.SignUpService;
import domain.DH_UserManagement.service.SignUpServiceImpl;
import domain.Inbound.controller.InboundSearchController;
import domain.Inbound.controller.InboundSearchControllerImp;
import domain.Inbound.repository.InboundSearchRepo;
import domain.Inbound.repository.InboundSearchRepoImp;
import domain.Inbound.service.InboundSearchService;
import domain.Inbound.service.InboundSearchServiceImp;
import domain.Inventory.controller.*;
import domain.Inventory.repository.*;
import domain.Inventory.service.*;
import dto.WarehouseDto;

public class Main {

    // 테스트용 메인
    public static void main(String[] args) {

         //inventory Test
        InventoryReadRepo repo = new InventoryReadRepoImp();
        InventoryReadService service = new InventoryReadServiceImp(repo);
        ValidCheck validCheck = new ValidCheck();
        InventoryReadController controller = new InventoryReadControllerImp(service,validCheck);
        InventoryUpdateRepo updateRepo = new InventoryUpdateRepoImp();
        InventoryUpdateService updateService = new InventoryUpdateServiceImp(updateRepo);
        InventoryUpdateController updateController = new InventoryUpdateCotrollerImp(updateService,validCheck);
        InventoryDeleteRepo deleteRepo = new InventoryDeleteRepoImp();
        InventoryDeleteService deleteService = new InventoryDeleteServiceImp(deleteRepo);
        InventoryDeleteController deleteController = new InventoryDeleteContollerImp(deleteService,validCheck);
        deleteController.deleteInventory();



//        SignUpRepository repository = new SignUpRepositoryImpl();
//        SignUpService service1 = new SignUpServiceImpl(repository);
//        SignUpController controller1 = new SignUpControllerImpl(validCheck, service1);
//        controller1.signUp();

//         InboundSearchRepo repo = new InboundSearchRepoImp(); // Repository 생성
//         InboundSearchService service = new InboundSearchServiceImp(repo);
//         ValidCheck validCheck = new ValidCheck();
//         InboundSearchController inboundSearchController =new InboundSearchControllerImp(service, validCheck);
//         inboundSearchController.SearchAll();



    }
}
