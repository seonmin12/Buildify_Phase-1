package domain.Inventory.service;

import common.ErrorCode;
import domain.Inventory.repository.InventoryDeleteRepo;
import domain.Inventory.repository.InventoryUpdateRepo;
import dto.InventoryDto;
import exception.InventoryException;
import exception.NotFoundException;


/**
 * {@link InventoryDeleteService} 인터페이스의 구현 클래스.
 * 재고 데이터를 삭제하는 비즈니스 로직을 처리하며,
 * 리포지토리로부터 삭제 결과를 받아 예외 처리 및 유효성 검사를 수행한다.
 */
public class InventoryDeleteServiceImp implements InventoryDeleteService {

    private InventoryDeleteRepo inventoryDeleteRepo;

    /**
     * InventoryDeleteServiceImp 생성자.
     *
     * @param inventoryDeleteRepo 재고 삭제를 위한 리포지토리 의존성 주입
     */
    public InventoryDeleteServiceImp(InventoryDeleteRepo inventoryDeleteRepo) {
        this.inventoryDeleteRepo = inventoryDeleteRepo;
    }

    /**
     * 지정된 상품 ID, 클라이언트 ID, 창고 ID에 해당하는 재고 데이터를 삭제한다.
     * 삭제된 재고 정보가 없을 경우 예외를 발생시킨다.
     *
     * @param prodID   삭제할 상품 ID
     * @param clientID 해당 상품의 클라이언트 ID
     * @param wareID   해당 상품의 창고 ID
     * @return 삭제된 재고 정보
     * @throws InventoryException 삭제 실패 또는 결과 없음 시 발생
     */
    @Override
    public InventoryDto deleteInventory(String prodID, String clientID, String wareID) {
        try {
            return inventoryDeleteRepo.deleteInventory(prodID,clientID,wareID).orElseThrow(()->new NotFoundException(String.valueOf(ErrorCode.ERROR_INPUT)));
        } catch (NotFoundException e) {
            throw new InventoryException(ErrorCode.ERROR_INPUT); //수정필요
        }
    }
}
