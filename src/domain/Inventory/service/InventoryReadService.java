package domain.Inventory.service;

import dto.InventoryDto;
import dto.WarehouseDto;

import java.util.List;
/**
 * 재고 데이터를 조회하기 위한 서비스 인터페이스.
 * 제품명, 클라이언트 ID, 카테고리 등을 기준으로 재고 정보를 조회하는 기능을 제공한다.
 */
public interface InventoryReadService {
    /**
     * 모든 재고 정보를 조회한다.
     *
     * @return 전체 재고 정보 리스트
     */
    List<InventoryDto> ReadAll();

    /**
     * 상품명을 기준으로 재고 정보를 조회한다.
     *
     * @param productName 조회할 상품명
     * @return 해당 상품명과 일치하는 재고 정보 리스트
     */
    List<InventoryDto> ReadByProductName(String productName);

    /**
     * 클라이언트 ID를 기준으로 재고 정보를 조회한다.
     *
     * @param clientID 조회할 클라이언트 ID
     * @return 해당 클라이언트 ID에 해당하는 재고 정보 리스트
     */
    List<InventoryDto> ReadByClientID(String clientID);

    /**
     * 카테고리를 기준으로 재고 정보를 조회한다.
     *
     * @param category 조회할 카테고리명
     * @return 해당 카테고리에 속하는 재고 정보 리스트
     */
    List<InventoryDto> ReadByCategory(String category);
}
