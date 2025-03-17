package domain.Inventory.controller;

import common.ValidCheck;
import domain.Inventory.service.InventoryDeleteService;
import dto.InventoryDto;

import javax.xml.validation.Validator;

public class InventoryDeleteContollerImp implements InventoryDeleteController {
    private final InventoryDeleteService inventoryDeleteService;
    private final ValidCheck validCheck;

    public InventoryDeleteContollerImp(InventoryDeleteService inventoryDeleteService, ValidCheck validCheck) {
        this.inventoryDeleteService = inventoryDeleteService;
        this.validCheck = validCheck;
    }

    @Override
    public InventoryDto deleteInventory() {
        System.out.println("[재고 삭제]");
        System.out.println("삭제할 재고의 상품 ID,client ID, 창고 ID를 입력하세요");
        System.out.print("상품ID를 입력하세요: ");
        String prodID = validCheck.inputAnyString();

        System.out.print("ClientID를 입력하세요: ");
        String clientID = validCheck.inputAnyString();

        System.out.print("창고ID를 입력하세요: ");
        String wareID = validCheck.inputAnyString();

        InventoryDto inventoryDto = inventoryDeleteService.deleteInventory(prodID, clientID, wareID);
        if(inventoryDto != null){
            System.out.println("재고 삭제 성공");


        }else System.out.println("해당 재고를 찾을 수 없습니다.");

        return inventoryDto;


    }
}
