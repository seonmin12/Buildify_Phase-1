package domain.Inventory.service;

import common.ErrorCode;
import domain.Inventory.repository.InventoryDeleteRepo;
import domain.Inventory.repository.InventoryUpdateRepo;
import dto.InventoryDto;
import exception.InventoryException;
import exception.NotFoundException;


/**
 * {@link InventoryDeleteService} 인터페이스의 구현 클래스.
 * 재고 데이터를 삭제한다
 */
public class InventoryDeleteServiceImp implements InventoryDeleteService {

    private InventoryDeleteRepo inventoryDeleteRepo;


    public InventoryDeleteServiceImp(InventoryDeleteRepo inventoryDeleteRepo) {
        this.inventoryDeleteRepo = inventoryDeleteRepo;
    }

    /**
     * 지정된 상품 ID, 클라이언트 ID, 창고 ID에 해당하는 재고 데이터를 삭제한다.
     *
     * @param prodID   삭제할 상품 ID
     * @param clientID 해당 상품의 클라이언트 ID
     * @param wareID   해당 상품의 창고 ID
     * @return 삭제된 재고 정보
     */
    @Override
    public InventoryDto deleteInventory(String prodID, String clientID, String wareID) {
        try {
            return inventoryDeleteRepo.deleteInventory(prodID,clientID,wareID).orElseThrow(()->new NotFoundException(String.valueOf(ErrorCode.ERROR_INPUT)));
        } catch (NotFoundException e) {
            return null;
        }
    }
}
