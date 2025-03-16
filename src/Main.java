import common.ValidCheck;
import controller.WarehouseController;
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

        InventoryReadRepo repo = new InventoryReadRepoImp();
        InventoryReadService service = new InventoryReadServiceImp(repo);
        ValidCheck validCheck = new ValidCheck();
        InventoryReadController controller = new InventoryReadControllerImp(service,validCheck);
        controller.ReadOneProductName();



    }
}
