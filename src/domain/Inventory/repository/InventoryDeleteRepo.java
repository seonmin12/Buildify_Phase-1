package domain.Inventory.repository;

import dto.InventoryDto;
import exception.InventoryException;

import java.util.Optional;

/**
 * 재고 데이터를 삭제하기 위한 repository 인터페이스.
 * 살품 ID, 클라이언트 ID, 창고 ID를 기준으로 재고 데이터를 삭제하는 기능을 제공한다.
 */
public interface InventoryDeleteRepo {
    /**
     * 지정된 상품 ID, 클라이언트 ID, 창고 ID에 해당하는 재고 데이터를 삭제한다.
     *
     * @param prodID   삭제할 싱픔 ID
     * @param clientID 해당 제품의 클라이언트 ID
     * @param wareID   해당 제품의 창고 ID
     * @return 삭제된 재고 정보를 담은 Optional 객체.
     *         해당 재고 정보가 없거나 삭제 실패 시 Optional.empty() 반환
     */
    Optional<InventoryDto> deleteInventory(String prodID, String clientID, String wareID);
}
