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
import domain.Inventory.controller.InventoryReadController;
import domain.Inventory.controller.InventoryReadControllerImp;
import domain.Inventory.repository.InventoryReadRepo;
import domain.Inventory.repository.InventoryReadRepoImp;
import domain.Inventory.service.InventoryReadService;
import domain.Inventory.service.InventoryReadServiceImp;
import dto.WarehouseDto;

public class Main {

    // 테스트용 메인
    public static void main(String[] args) {

//        InventoryReadRepo repo = new InventoryReadRepoImp();
//        InventoryReadService service = new InventoryReadServiceImp(repo);
//        ValidCheck validCheck = new ValidCheck();
//        InventoryReadController controller = new InventoryReadControllerImp(service,validCheck);
//        controller.ReadByCategory();

//        SignUpRepository repository = new SignUpRepositoryImpl();
//        SignUpService service1 = new SignUpServiceImpl(repository);
//        SignUpController controller1 = new SignUpControllerImpl(validCheck, service1);
//        controller1.signUp();

        InboundSearchRepo repo = new InboundSearchRepoImp(); // Repository 생성
        InboundSearchService service = new InboundSearchServiceImp(repo);
        ValidCheck validCheck = new ValidCheck();
        InboundSearchController inboundSearchController =new InboundSearchControllerImp(service, validCheck);
        inboundSearchController.SearchAll();



    }
}
