package domain.Inventory.controller;

import dto.InventoryDto;
import dto.WarehouseDto;

import java.util.List;
import java.util.Optional;

/**
 * 사용자 입력을 받아 재고 정보를 조회하고 화면에 출력하는 컨트롤러 인터페이스.
 * 입력은 메서드 내부에서 직접 받아 처리한다.
 */
public interface InventoryReadController {
    /**
     * 사용자로부터 상품명을 입력받아 재고 정보를 조회한다.
     *
     * @return 입력된 상품명으로 조회된 재고 정보 리스트
     */
    List<InventoryDto> ReadByProductName();

    /**
     * 전체 재고 정보를 조회하여 반환한다.
     *
     * @return 전체 재고 정보 리스트
     */
    List<InventoryDto> ReadAll();

    /**
     * 사용자로부터 클라이언트 ID를 입력받아 재고 정보를 조회한다.
     *
     * @return 입력된 클라이언트 ID로 조회된 재고 정보 리스트
     */
    List<InventoryDto> ReadByClientID();

    /**
     * 사용자로부터 카테고리를 입력받아 재고 정보를 조회한다.
     *
     * @return 입력된 카테고리로 조회된 재고 정보 리스트
     */
    List<InventoryDto> ReadByCategory();

}
