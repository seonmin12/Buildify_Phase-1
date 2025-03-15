package domain.Inventory.controller;

import common.ValidCheck;
import domain.Inventory.service.InventoryReadService;
import domain.Inventory.service.InventoryReadServiceImp;
import dto.WarehouseDto;

import java.util.List;

public class InventoryReadControllerImp implements InventoryReadController {

    private final InventoryReadService inventoryReadService;
    private final ValidCheck validCheck;

    public InventoryReadControllerImp(InventoryReadService inventoryReadService, ValidCheck validCheck) {
        this.inventoryReadService = inventoryReadService;
        this.validCheck = validCheck;
    }

    @Override
    public WarehouseDto ReadOne(String id) {

        return null;

    }

    @Override
    public void ReadAll() {

        List<WarehouseDto> warehouseDtoList = inventoryReadService.ReadAll();

        if(warehouseDtoList.isEmpty()){
            System.out.println("저장된 재고 정보가 없습니다");
            return;
        }

        System.out.println("전체 재고 정보를 검색합니다.");
        System.out.println("테스트입니다");
        System.out.println("나중에 업데이트 예정");

        for(WarehouseDto warehouseDto : warehouseDtoList){
            System.out.printf(
                    "상품명:%-8s | 창고ID:%-6s | 회원(입점사)ID:%-6s | 상품ID:%-8s | 재고:%4d | 최종출고일:%-10s | 최종입고일:%-10s\n",
                    warehouseDto.getProd_name(),
                    warehouseDto.getWare_id(),
                    warehouseDto.getClient_id(),
                    warehouseDto.getProd_id(),
                    warehouseDto.getQuantity(),
                    warehouseDto.getLast_outbount_day(),   // Date 타입 -> toString()으로 출력
                    warehouseDto.getLast_inbound_day()     // Date 타입 -> toString()으로 출력
            );
        }

    }
}
