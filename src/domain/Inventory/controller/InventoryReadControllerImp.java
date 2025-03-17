package domain.Inventory.controller;

import common.ValidCheck;
import domain.Inventory.service.InventoryReadService;
import dto.InventoryDto;
import dto.WarehouseDto;

import java.util.*;

public class InventoryReadControllerImp implements InventoryReadController {

    private final InventoryReadService inventoryReadService;
    private final ValidCheck validCheck;

    public InventoryReadControllerImp(InventoryReadService inventoryReadService, ValidCheck validCheck) {
        this.inventoryReadService = inventoryReadService;
        this.validCheck = validCheck;
    }


    @Override
    public List<InventoryDto> ReadByProductName() {
        System.out.print("조회할 상품명을 입력하세요: ");
        String productName = validCheck.inputAnyString();

        List<InventoryDto> inventoryDtoList = inventoryReadService.ReadByProductName(productName);


        if (inventoryDtoList == null || inventoryDtoList.isEmpty()) {
            System.out.println("해당 카테고리의 재고가 없습니다.");
            return null;
        }

        for (InventoryDto dto : inventoryDtoList) {
            System.out.printf("상품명:%-8s | 창고ID:%-6s | 입점사ID:%-6s | 상품ID:%-8s | 재고:%4d | 최종출고일:%s | 최종입고일:%s\n",
                    dto.getProd_name(), dto.getWare_id(), dto.getClient_id(),
                    dto.getProd_id(), dto.getQuantity(), dto.getLast_outbount_day(), dto.getLast_inbound_day());
        }
        return inventoryDtoList;
    }

        @Override
        public List<InventoryDto> ReadAll () {

            List<InventoryDto> inventoryDtoList = inventoryReadService.ReadAll();

            if (inventoryDtoList.isEmpty()) {
                System.out.println("저장된 재고 정보가 없습니다");
                return null;
            }

            System.out.println("전체 재고 정보를 검색합니다.");
            System.out.println("테스트입니다");
            System.out.println("나중에 업데이트 예정");

            for (InventoryDto inventoryDto : inventoryDtoList) {
                System.out.printf(
                        "상품명:%-8s | 창고ID:%-6s | 회원(입점사)ID:%-6s | 상품ID:%-8s | 재고:%4d | 최종출고일:%-10s | 최종입고일:%-10s\n",
                        inventoryDto.getProd_name(),
                        inventoryDto.getWare_id(),
                        inventoryDto.getClient_id(),
                        inventoryDto.getProd_id(),
                        inventoryDto.getQuantity(),
                        inventoryDto.getLast_outbount_day(),
                        inventoryDto.getLast_inbound_day()
                );
            }

            return null;
        }

        @Override
        public List<InventoryDto> ReadByClientID () {

            System.out.print("고객 ID(client_id)를 입력하세요: ");
            String clientId = validCheck.inputAnyString();

            List<InventoryDto> inventoryDtoList = inventoryReadService.ReadByClientID(clientId);


            if (inventoryDtoList == null || inventoryDtoList.isEmpty()) {
                System.out.println("해당 카테고리의 재고가 없습니다.");
                return null;
            }

            for (InventoryDto dto : inventoryDtoList) {
                System.out.printf("상품명:%-8s | 창고ID:%-6s | 입점사ID:%-6s | 상품ID:%-8s | 수량:%4d | 최종출고일:%s | 최종입고일:%s\n",
                        dto.getProd_name(), dto.getWare_id(), dto.getClient_id(),
                        dto.getProd_id(), dto.getQuantity(), dto.getLast_outbount_day(), dto.getLast_inbound_day());
            }
            return inventoryDtoList;

        }

        @Override
        public List<InventoryDto> ReadByCategory () {

            System.out.println("대분류를 선택하세요: ");
            System.out.println("1. PC 주요 부품");
            System.out.println("2. 주변기기");
            int majorChoice = validCheck.inputNumRegex();

            List<String> categories = new ArrayList<>();
            if (majorChoice == 1) {
                categories = Arrays.asList("cpu", "메인보드", "램", "그래픽카드");
            } else if (majorChoice == 2) {
                categories = Arrays.asList("모니터", "키보드", "마우스");
            } else {
                System.out.println("잘못된 선택입니다.");
                return null;
            }
            System.out.println("소분류를 선택하세요: ");
            for (int i = 0; i < categories.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, categories.get(i));
            }
            int subchoice = validCheck.inputNumRegex();
            if (subchoice < 1 || subchoice > categories.size()) {
                System.out.println("잘못된 선택입니다.");
                return null;
            }

            String selectedCategory = categories.get(subchoice - 1);
            List<InventoryDto> inventoryDtoList = inventoryReadService.ReadByCategory(selectedCategory);
            if (inventoryDtoList == null || inventoryDtoList.isEmpty()) {
                System.out.println("해당 카테고리의 재고가 없습니다.");
                return null;
            }
            for (InventoryDto dto : inventoryDtoList) {
                System.out.printf("상품명:%-8s | 창고ID:%-6s | 입점사ID:%-6s | 상품ID:%-8s | 재고:%4d | 최종출고일:%s | 최종입고일:%s\n",
                        dto.getProd_name(), dto.getWare_id(), dto.getClient_id(),
                        dto.getProd_id(), dto.getQuantity(), dto.getLast_outbount_day(), dto.getLast_inbound_day());
            }
            return inventoryDtoList;


        }
    }

