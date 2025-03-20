package domain.Inventory.service;

import common.ErrorCode;
import domain.Inventory.repository.InventoryUpdateRepo;
import dto.InventoryDto;
import exception.InventoryException;
import exception.NotFoundException;


/**
 * {@link InventoryUpdateService} 인터페이스의 구현 클래스.
 * 재고 수량을 업데이트하는 기능을 수행한다.
 */
public class InventoryUpdateServiceImp implements InventoryUpdateService {

    private InventoryUpdateRepo inventoryUpdateRepo;


    public InventoryUpdateServiceImp(InventoryUpdateRepo inventoryUpdateRepo) {
        this.inventoryUpdateRepo = inventoryUpdateRepo;
    }

    /**
     * 지정된 상품 ID, 클라이언트 ID, 창고 ID에 해당하는 재고 수량을 업데이트한다.
     *
     * @param prodID      수정할 상품 ID
     * @param clientID    해당 제품의 클라이언트 ID
     * @param wareID      해당 제품의 창고 ID
     * @param newQuantity 수정할 수량 값
     * @return 업데이트된 재고 정보
     */
    @Override
    public InventoryDto updateInventory(String prodID, String clientID, String wareID, int newQuantity) {
        try {
            return inventoryUpdateRepo.updateQuantity(prodID,clientID,wareID,newQuantity).orElseThrow(()->new NotFoundException(String.valueOf(ErrorCode.ERROR_INPUT)));
        } catch (NotFoundException e) {
            return null;
        }
    }
}
