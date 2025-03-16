package domain.Inventory.controller;

import common.ValidCheck;
import domain.Inventory.service.InventoryReadService;
import dto.WarehouseDto;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class InventoryReadControllerImp implements InventoryReadController {

    private final InventoryReadService inventoryReadService;
    private final ValidCheck validCheck;

    public InventoryReadControllerImp(InventoryReadService inventoryReadService, ValidCheck validCheck) {
        this.inventoryReadService = inventoryReadService;
        this.validCheck = validCheck;
    }


    @Override
    public WarehouseDto ReadOneProductName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("찾으려는 상품명을 입력하세요: ");
        String productName = scanner.nextLine();

        WarehouseDto warehouseDto = inventoryReadService.ReadOneProductName(productName);



        if(warehouseDto == null) {
            System.out.println("정보를 찾을 수 없습니다.");
            return null;
        }
        System.out.printf(  "상품명:%-8s | 창고ID:%-6s | 회원(입점사)ID:%-6s | 상품ID:%-8s | 재고:%4d | 최종출고일:%-10s | 최종입고일:%-10s\n",
                warehouseDto.getProd_name(),
                warehouseDto.getWare_id(),
                warehouseDto.getClient_id(),
                warehouseDto.getProd_id(),
                warehouseDto.getQuantity(),
                warehouseDto.getLast_outbount_day(),
                warehouseDto.getLast_inbound_day() );
        return warehouseDto;
    }

    @Override
    public List<WarehouseDto> ReadAll() {

        List<WarehouseDto> warehouseDtoList = inventoryReadService.ReadAll();

        if(warehouseDtoList.isEmpty()){
            System.out.println("저장된 재고 정보가 없습니다");
            return null;
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
                    warehouseDto.getLast_outbount_day(),
                    warehouseDto.getLast_inbound_day()
            );
        }

        return null;
    }

    @Override
    public WarehouseDto ReadByClientID() {
      Scanner scanner = new Scanner(System.in);
        System.out.print("고객 ID(client_id)를 입력하세요: ");
        String clientId = scanner.nextLine();
        WarehouseDto warehouseDto = inventoryReadService.ReadByClientID(clientId);
        if(warehouseDto == null) {
            System.out.println("정보를 찾을 수 없습니다.");
            return null;
        }
        System.out.printf(  "상품명:%-8s | 창고ID:%-6s | 회원(입점사)ID:%-6s | 상품ID:%-8s | 재고:%4d | 최종출고일:%-10s | 최종입고일:%-10s\n",
                warehouseDto.getProd_name(),
                warehouseDto.getWare_id(),
                warehouseDto.getClient_id(),
                warehouseDto.getProd_id(),
                warehouseDto.getQuantity(),
                warehouseDto.getLast_outbount_day(),
                warehouseDto.getLast_inbound_day() );
        return warehouseDto;

    }
}
