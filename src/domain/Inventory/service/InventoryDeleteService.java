package domain.Inventory.service;

import dto.InventoryDto;

/**
 * 재고 데이터를 삭제하기 위한 서비스 인터페이스.
 * 상품 ID, 클라이언트 ID, 창고 ID를 기준으로 재고 데이터를 삭제하는 기능을 제공한다.
 */
public interface InventoryDeleteService {

    /**
     * 지정된 상품 ID, 클라이언트 ID, 창고 ID에 해당하는 재고 데이터를 삭제한다.
     *
     * @param prodID   삭제할 상품 ID
     * @param clientID 해당 상품의 클라이언트 ID
     * @param wareID   해당 상품의 창고 ID
     * @return 삭제된 재고 정보
     */
    InventoryDto deleteInventory(String prodID, String clientID, String wareID);
}
