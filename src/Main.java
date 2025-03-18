import common.ValidCheck;

import domain.Inventory.controller.*;
import domain.Inventory.repository.*;
import domain.Inventory.service.*;

public class Main {

    // 테스트용 메인
    public static void main(String[] args) {

         //inventory Test
        InventoryReadRepo repo = new InventoryReadRepoImp();
        InventoryReadService service = new InventoryReadServiceImp(repo);
        ValidCheck validCheck = new ValidCheck();
        InventoryReadController readcontroller = new InventoryReadControllerImp(service,validCheck);
        InventoryUpdateRepo updateRepo = new InventoryUpdateRepoImp();
        InventoryUpdateService updateService = new InventoryUpdateServiceImp(updateRepo);
        InventoryUpdateController updateController = new InventoryUpdateControllerImp(updateService,validCheck);
        InventoryDeleteRepo deleteRepo = new InventoryDeleteRepoImp();
        InventoryDeleteService deleteService = new InventoryDeleteServiceImp(deleteRepo);
        InventoryDeleteController deleteController = new InventoryDeleteControllerImp(deleteService,validCheck);



        // 통합 컨트롤러에 주입
        InventoryIntegratedController integratedController = new InventoryIntegratedControllerImp(
                readcontroller,
                updateController,
                deleteController,
                validCheck
        );

        // 실행
        integratedController.inventoryRunForUser();





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
