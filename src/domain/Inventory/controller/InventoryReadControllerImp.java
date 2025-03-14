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
            System.out.printf("%s %-10S %2s %8d %6S %5S\n",
                    warehouseDto.getWare_id(), warehouseDto.getClient_id(),
                    warehouseDto.getProd_id(), warehouseDto.getQuantity(),
                    warehouseDto.getLast_outbount_day(),warehouseDto.getLast_inbound_day());
        }

    }
}
