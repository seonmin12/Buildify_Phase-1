package domain.Inventory.controller;

import common.ValidCheck;
import domain.Inventory.service.InventoryUpdateService;
import dto.InventoryDto;

public class InventoryUpdateControllerImp implements InventoryUpdateController {
    private final InventoryUpdateService inventoryUpdateService;
    private final ValidCheck validCheck;

    public InventoryUpdateControllerImp(InventoryUpdateService inventoryUpdateService, ValidCheck validCheck) {
        this.inventoryUpdateService = inventoryUpdateService;
        this.validCheck = validCheck;
    }

    @Override
    public InventoryDto updateQuantity() {
        System.out.println("[수량 업데이트]");
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
        if(inventoryDto != null){
            System.out.println("수정된 재고 정보: ");
            System.out.printf("상품명:%-8s | 창고ID:%-6s | 입점사ID:%-6s | 상품ID:%-8s | 재고:%4d | 최종출고일:%s | 최종입고일:%s\n",
                   inventoryDto.getProd_name(), inventoryDto.getProd_id(), inventoryDto.getWare_id(),inventoryDto.getClient_id(),
                    inventoryDto.getQuantity(),inventoryDto.getLast_outbount_day(), inventoryDto.getLast_inbound_day());

        }else System.out.println("해당 재고를 찾을 수 없습니다.");

        return inventoryDto;

    }

}
