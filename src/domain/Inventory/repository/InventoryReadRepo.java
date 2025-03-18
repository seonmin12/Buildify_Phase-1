package domain.Inventory.repository;

import dto.InventoryDto;
import dto.WarehouseDto;

import java.util.List;
import java.util.Optional;


/**
 * 재고 데이터를 읽기 위한 repository 인터페이스
 * 상품명, 클라이언트 ID, 카테고리 등을 기준으로 재고 정보를 조회할 수 있는 메서드와 전체 조회를 제공하는 메소드를 제공한다.
 */
public interface InventoryReadRepo {

    /**
     * 모든 재고 데이터를 조회한다.
     *
     * @return 재고 정보 리스트를 포함한 Optional 객체.
     *         데이터가 없을 경우 빈 Optional이 반환된다.
     */
    Optional<List<InventoryDto>> ReadAll();


    /**
     * 상품명을 기준으로 재고 데이터를 조회한다.
     *
     * @param productName 조회할 상품명
     * @return 해당 상품명과 일치하는 재고 정보 리스트
     */
    List<InventoryDto> ReadByProductName(String productName);

    /**
     * 클라이언트 ID(고객 ID)를 기준으로 재고 데이터를 조회한다.
     *
     * @param clientID 조회할 클라이언트 ID
     * @return 해당 클라이언트 ID에 해당하는 재고 정보 리스트
     */
    List <InventoryDto> ReadByClientID(String clientID);

    /**
     * 카테고리를 기준으로 재고 데이터를 조회한다.
     *
     * @param category 조회할 카테고리명
     * @return 해당 카테고리에 속하는 재고 정보 리스트
     */
    List<InventoryDto> ReadByCategory(String category);
}
