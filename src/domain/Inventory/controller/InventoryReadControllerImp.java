package domain.Inventory.controller;

import common.ValidCheck;
import domain.Inventory.service.InventoryReadService;
import dto.InventoryDto;
import dto.UserDto;
import dto.WarehouseDto;

import java.util.*;

/**
 * 재고 정보를 조회하고 출력하는 컨트롤러 구현 클래스.
 * 사용자로부터 입력을 받아 서비스 계층에 전달하고, 결과를 포맷팅하여 출력한다.
 */
public class InventoryReadControllerImp implements InventoryReadController {

    private final InventoryReadService inventoryReadService;
    private final ValidCheck validCheck;


    public InventoryReadControllerImp(InventoryReadService inventoryReadService, ValidCheck validCheck) {
        this.inventoryReadService = inventoryReadService;
        this.validCheck = validCheck;
    }


    /**
     * 사용자로부터 상품명을 입력받아 재고 정보를 조회하고 출력한다.
     *
     * @return 조회된 재고 정보 리스트, 없을 경우 null 반환
     */
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

    /**
     * 전체 재고 정보를 조회하여 출력한다.
     *
     * @return 조회된 재고 정보 리스트, 없을 경우 null 반환
     */
        @Override
        public List<InventoryDto> ReadAll () {

            List<InventoryDto> inventoryDtoList = inventoryReadService.ReadAll();

            if (inventoryDtoList == null || inventoryDtoList.isEmpty()) {
                System.out.println("저장된 재고 정보가 없습니다");
                return null;
            }

            System.out.println("[전체 재고 정보 조회]");


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

    /**
     * 관리자로부터 클라이언트 ID를 입력받아 재고 정보를 조회하고 출력한다.
     *
     * @return 조회된 재고 정보 리스트, 없을 경우 null
     */
    @Override
    public List<InventoryDto> ReadByClientID() {
        System.out.print("조회할 입점사 ID(Client_id)를 입력하세요: ");
        String client_id = validCheck.inputAnyString();
        if (client_id == null || client_id.isEmpty()) {
            System.out.println("해당 입점사ID(clientID)를 찾을 수 없습니다.");
            return null;
        }
        return processInventoryRead(client_id);
    }


    /**
     * 사용자로부터 클라이언트 ID를 입력받아 재고 정보를 조회하고 출력한다.
     *
     * @return 조회된 재고 정보 리스트, 없을 경우 null 반환
     */
        @Override
        public List<InventoryDto> ReadByClientID (UserDto userDto) {

            String clientId = userDto.getClient_id();
            if (clientId == null || clientId.isEmpty()) {
                System.out.println("해당 clientID를 찾을 수 없습니다");
                return null;
            }


           return processInventoryRead(clientId);

        }

    /**
     * 사용자로부터 대분류 및 소분류를 선택받아 해당 카테고리의 재고 정보를 조회하고 출력한다.
     *
     * @return 조회된 재고 정보 리스트, 없을 경우 null 반환
     */
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
    /**
     * 클라이언트 ID로 재고 정보를 조회하고 출력한다.
     * user 와 관리자 공통으로 재고 정보를 조회하고 출력한다.
     * @param clientId 조회할 클라이언트 ID
     * @return 조회된 재고 정보 리스트, 없을 경우 null
     */
    @Override
    public List<InventoryDto> processInventoryRead(String clientId) {
        List<InventoryDto> inventoryDtoList = inventoryReadService.ReadByClientID(clientId);

        if (inventoryDtoList == null || inventoryDtoList.isEmpty()) {

            return null;
        }

        for (InventoryDto dto : inventoryDtoList) {
            System.out.printf("상품명:%-8s | 창고ID:%-6s | 입점사ID:%-6s | 상품ID:%-8s | 수량:%4d | 최종출고일:%s | 최종입고일:%s\n",
                    dto.getProd_name(), dto.getWare_id(), dto.getClient_id(),
                    dto.getProd_id(), dto.getQuantity(), dto.getLast_outbount_day(), dto.getLast_inbound_day());
        }

        return inventoryDtoList;
    }
}

