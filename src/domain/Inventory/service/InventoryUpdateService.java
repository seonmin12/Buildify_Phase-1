package domain.Inventory.service;

import dto.InventoryDto;

/**
 * 재고 데이터를 수정하기 위한 서비스 인터페이스.
 * 상품 ID, 클라이언트 ID, 창고 ID를 기준으로 재고 수량을 업데이트하는 기능을 제공한다.
 */
public interface InventoryUpdateService {

    /**
     * 지정된 상품 ID, 클라이언트 ID, 창고 ID에 해당하는 재고 수량을 업데이트한다.
     *
     * @param prodID      수정할 제품 ID
     * @param clientID    해당 제품의 클라이언트 ID
     * @param wareID      해당 제품의 창고 ID
     * @param newQuantity 수정할 수량 값
     * @return 업데이트된 재고 정보
     */
    InventoryDto updateInventory(String prodID, String clientID, String wareID, int newQuantity);
}
