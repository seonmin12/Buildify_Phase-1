package domain.Inventory.repository;

import config.DBConnection;
import dto.InventoryDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Optional;


/**
 * 재고 데이터의 수량을 수정하기 위한 repository 인터페이스.
 * 특정 상품의 수량을 업데이트하는 기능을 제공한다.
 */
public interface InventoryUpdateRepo {

    /**
     * 지정된 상품 ID, 클라이언트 ID, 창고 ID에 해당하는 재고의 수량을 업데이트한다.
     *
     * @param prodID       수량을 수정할 상품 ID
     * @param clientID     해당 제품의 클라이언트 ID
     * @param wareID       해당 제품의 창고 ID
     * @param newQuantity  수정할 새로운 수량 값
     * @return 업데이트된 재고 정보를 포함한 Optional 객체.
     *         업데이트 실패 시 Optional.empty()가 반환될 수 있다.
     */
    Optional<InventoryDto> updateQuantity(String prodID, String clientID, String wareID, int newQuantity);
}
