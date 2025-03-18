package domain.Inventory.controller;

import dto.InventoryDto;



/**
 * 재고 수량을 수정하는 컨트롤러 인터페이스.
 * 사용자로부터 입력을 받아 서비스 계층에 전달하고,
 * 수정된 재고 정보를 반환하는 역할을 한다.
 */
public interface InventoryUpdateController {

    /**
     * 사용자로부터 제품 ID, 클라이언트 ID, 창고 ID, 수정할 수량을 입력받아 재고 수량을 업데이트한다.
     *
     * @return 수정된 재고 정보
     */
    InventoryDto updateQuantity();


}
