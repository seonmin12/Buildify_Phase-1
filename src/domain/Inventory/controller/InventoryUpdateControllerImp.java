package domain.Inventory.controller;

import common.ValidCheck;
import domain.Inventory.service.InventoryReadService;
import domain.Inventory.service.InventoryUpdateService;
import dto.InventoryDto;

import java.util.List;
/**
 * 재고 수량 업데이트 기능을 구현한 컨트롤러 클래스.
 * <p>
 * 사용자로부터 재고 정보와 수정할 수량을 입력을 받아 재고 수량을 수정하고 결과를 출력한다.
 */
public class InventoryUpdateControllerImp implements InventoryUpdateController {
    private final InventoryUpdateService inventoryUpdateService;
    private final ValidCheck validCheck;
    private final InventoryReadService inventoryReadService;

    public InventoryUpdateControllerImp(InventoryUpdateService inventoryUpdateService, ValidCheck validCheck, InventoryReadService inventoryReadService) {
        this.inventoryUpdateService = inventoryUpdateService;
        this.validCheck = validCheck;
        this.inventoryReadService = inventoryReadService;
    }

    /**
     * 사용자로부터 재고 정보를 입력받아 수량을 업데이트한다.
     * <p>
     * 수정 결과를 출력하고, 수정된 재고 정보를 반환한다.
     *
     * @return 수정된 재고 정보
     */
    @Override
    public InventoryDto updateQuantity() {
        System.out.println("[수량 업데이트]");
        List<InventoryDto> inventoryList = inventoryReadService.ReadAll();
        if(inventoryList.isEmpty()){
            System.out.println("현재 등록된 재고가 없습니다.");
            return null;
        }
        System.out.println("\n [현재 재고 목록] ");
        for (InventoryDto dto : inventoryList) {
            System.out.printf("상품명:%-8s | 창고ID:%-6s | 입점사ID:%-6s | 상품ID:%-8s | 재고:%4d | 최종출고일:%s | 최종입고일:%s\n",
                    dto.getProd_name(), dto.getWare_id(), dto.getClient_id(),
                    dto.getProd_id(), dto.getQuantity(), dto.getLast_outbount_day(), dto.getLast_inbound_day());
        }
        System.out.println("==============================================================================================================");
        System.out.println("수정할 재고의 상품ID, clientID, 창고ID, 새로운 수량을 차례로 입력하세요");
        System.out.print("상품ID를 입력하세요: ");
        String prodID = validCheck.inputAnyString();

        System.out.print("ClientID를 입력하세요: ");
        String clientID = validCheck.inputAnyString();

        System.out.print("창고ID를 입력하세요: ");
        String wareID = validCheck.inputAnyString();

        System.out.print("변경할 수량을 입력하세요: ");
        int newQuantity = validCheck.inputNumRegex();


        InventoryDto inventoryDto = inventoryUpdateService.updateInventory(prodID,clientID,wareID,newQuantity);
        if(inventoryDto == null){
            System.out.println("해당 재고를 찾을 수 없습니다.");
        }else{
            System.out.println("수정 성공");
        }

        return inventoryDto;

    }

}
