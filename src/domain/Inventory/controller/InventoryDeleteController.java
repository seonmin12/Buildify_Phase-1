package domain.Inventory.controller;

import dto.InventoryDto;
/**
 * 재고 삭제 컨트롤러 인터페이스.
 */
public interface InventoryDeleteController {
    /**
     * 재고 정보를 삭제한다.
     *
     * @return 삭제된 재고 정보
     */
    InventoryDto deleteInventory();
}
