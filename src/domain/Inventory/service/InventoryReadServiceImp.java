package domain.Inventory.service;

import common.ErrorCode;
import domain.Inventory.repository.InventoryReadRepo;
import dto.InventoryDto;
import dto.WarehouseDto;
import exception.InventoryException;
import exception.NotFoundException;

import java.util.List;

/**
 * {@link InventoryReadService} 인터페이스의 구현 클래스.
 * 재고 데이터를 조회하는 비즈니스 로직을 처리하며,
 * repository 로부터 데이터를 가져와 예외 처리 및 유효성 검사를 수행한다.
 */
public class InventoryReadServiceImp implements InventoryReadService {
    private InventoryReadRepo inventoryReadRepo;


    /**
     * InventoryReadServiceImp 생성자.
     *
     * @param inventoryReadRepo 재고 조회를 위한 Repository 의존성 주입
     */
    public InventoryReadServiceImp(InventoryReadRepo inventoryReadRepo){
        this.inventoryReadRepo = inventoryReadRepo;
    }


    /**
     * 전체 재고 정보를 조회한다.
     * 조회 결과가 없을 경우 예외를 발생시킨다.
     *
     * @return 전체 재고 정보 리스트
     * @throws InventoryException 조회 실패 시 발생
     */
    @Override
    public List<InventoryDto> ReadAll() {
        try {
            return inventoryReadRepo.ReadAll().orElseThrow(() -> new NotFoundException(String.valueOf(ErrorCode.ERROR_INPUT)));
        } catch (NotFoundException e) {
            throw new InventoryException(ErrorCode.DB_INVENTORY_READ_ALL_ERROR);
        }


    }

    /**
     * 상품명을 기준으로 재고 정보를 조회한다.
     * 결과가 없을 경우 예외를 발생시킨다.
     *
     * @param productName 조회할 상품명
     * @return 해당 상품명과 일치하는 재고 정보 리스트
     * @throws InventoryException 조회 실패 시 발생
     */
    @Override
    public List<InventoryDto> ReadByProductName(String productName) {
        List<InventoryDto> inventoryList = inventoryReadRepo.ReadByProductName(productName);

        if (inventoryList == null || inventoryList.isEmpty()) {
            throw new InventoryException(ErrorCode.ERROR_INPUT);
        }

        return inventoryList;
    }

    /**
     * 클라이언트 ID를 기준으로 재고 정보를 조회한다.
     * 결과가 없을 경우 예외를 발생시킨다.
     *
     * @param clientID 조회할 클라이언트 ID
     * @return 해당 클라이언트 ID에 해당하는 재고 정보 리스트
     * @throws InventoryException 조회 실패 시 발생
     */
    @Override
    public List <InventoryDto> ReadByClientID(String clientID) {
        List<InventoryDto> inventoryList = inventoryReadRepo.ReadByClientID(clientID);
        System.out.println(inventoryList);

        if (inventoryList == null || inventoryList.isEmpty()) {
            System.out.println("재고목록이 없습니다.");
        }

        return inventoryList;

    }

    /**
     * 카테고리를 기준으로 재고 정보를 조회한다.
     * 결과가 없을 경우 예외를 발생시킨다.
     *
     * @param category 조회할 카테고리명
     * @return 해당 카테고리에 속하는 재고 정보 리스트
     * @throws InventoryException 조회 실패 시 발생
     */
    @Override
    public List<InventoryDto> ReadByCategory(String category) {
        List<InventoryDto> inventoryList = inventoryReadRepo.ReadByCategory(category);

        if (inventoryList == null || inventoryList.isEmpty()) {
            throw new InventoryException(ErrorCode.ERROR_INPUT);
        }

        return inventoryList;
    }
}
