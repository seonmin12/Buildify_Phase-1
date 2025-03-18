package domain.Inventory.service;

import common.ErrorCode;
import domain.Inventory.repository.InventoryUpdateRepo;
import dto.InventoryDto;
import exception.InventoryException;
import exception.NotFoundException;


/**
 * {@link InventoryUpdateService} 인터페이스의 구현 클래스.
 * 재고 수량을 업데이트하는 비즈니스 로직을 처리하며,
 * 리포지토리로부터 업데이트된 데이터를 받아 예외 처리 및 유효성 검사를 수행한다.
 */
public class InventoryUpdateServiceImp implements InventoryUpdateService {

    private InventoryUpdateRepo inventoryUpdateRepo;

    /**
     * InventoryUpdateServiceImp 생성자.
     *
     * @param inventoryUpdateRepo 재고 수량 업데이트를 위한 repository 의존성 주입
     */
    public InventoryUpdateServiceImp(InventoryUpdateRepo inventoryUpdateRepo) {
        this.inventoryUpdateRepo = inventoryUpdateRepo;
    }

    /**
     * 지정된 상품 ID, 클라이언트 ID, 창고 ID에 해당하는 재고 수량을 업데이트한다.
     * 업데이트된 재고 정보가 없을 경우 예외를 발생시킨다.
     *
     * @param prodID      수정할 상품 ID
     * @param clientID    해당 제품의 클라이언트 ID
     * @param wareID      해당 제품의 창고 ID
     * @param newQuantity 수정할 수량 값
     * @return 업데이트된 재고 정보
     * @throws InventoryException 업데이트 실패 또는 결과 없음 시 발생
     */
    @Override
    public InventoryDto updateInventory(String prodID, String clientID, String wareID, int newQuantity) {
        try {
            return inventoryUpdateRepo.updateQuantity(prodID,clientID,wareID,newQuantity).orElseThrow(()->new NotFoundException(String.valueOf(ErrorCode.ERROR_INPUT)));
        } catch (NotFoundException e) {
            throw new InventoryException(ErrorCode.ERROR_INPUT);
        }
    }
}
