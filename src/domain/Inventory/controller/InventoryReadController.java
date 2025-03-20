package domain.Inventory.controller;

import dto.InventoryDto;
import dto.UserDto;
import dto.WarehouseDto;

import java.util.List;
import java.util.Optional;

/**
 * 사용자 입력을 받아 재고 정보를 조회하고 화면에 출력하는 컨트롤러 인터페이스.
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
     * 관리자로부터 클라이언트 ID를 입력받아 재고 정보를 조회한다.
     *
     * @return 입력된 클라이언트 ID로 조회된 재고 정보 리스트
     */
    List<InventoryDto> ReadByClientID();

    /**
     * 회원의 클라이언트 ID를 이용해 재고 정보를 조회한다.
     *
     * @param userDto 회원 정보
     * @return 해당 회원의 재고 정보 리스트
     */
    List<InventoryDto>ReadByClientID(UserDto userDto);

    /**
     * 사용자로부터 카테고리를 입력받아 재고 정보를 조회한다.
     *
     * @return 입력된 카테고리로 조회된 재고 정보 리스트
     */
    List<InventoryDto> ReadByCategory();

    /**
     * 클라이언트 ID로 재고 정보를 조회하고 출력한다.
     *
     * @param clientId 조회할 클라이언트 ID
     * @return 조회된 재고 정보 리스트
     */
    List<InventoryDto> processInventoryRead(String clientId);

}
