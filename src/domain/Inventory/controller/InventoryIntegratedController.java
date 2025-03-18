package domain.Inventory.controller;

import common.ValidCheck;
import dto.AdminDto;
import dto.UserDto;

public class InventoryIntegratedController {

    private final InventoryReadController inventoryReadController;
    private final InventoryUpdateController inventoryUpdateController;
    private final InventoryDeleteController inventoryDeleteController;
    private final ValidCheck validCheck;

    public InventoryIntegratedController(InventoryReadController inventoryReadController, InventoryUpdateController inventoryUpdateController
            , InventoryDeleteController inventoryDeleteController, ValidCheck validCheck) {
        this.inventoryReadController = inventoryReadController;
        this.inventoryUpdateController = inventoryUpdateController;
        this.inventoryDeleteController = inventoryDeleteController;
        this.validCheck = validCheck;
    }




    public void inventoryRunForAdmin(AdminDto adminDto) {

        while(true){
            System.out.println("\n=== 재고 통합 관리 메뉴 ===");
            System.out.println("1. 재고 조회");
            System.out.println("2. 재고 삭제");
            System.out.println("3. 재고 수정");
            System.out.println("0. 종료");
            System.out.print("선택 > ");
            int choice = validCheck.inputNumRegex();

            switch (choice) {
                case 1:
                    inventoryReadMenu(adminDto);
                    break;

                    case 2:
                        inventoryDeleteController.deleteInventory();
                        break;

                        case 3:
                            inventoryUpdateController.updateQuantity();
                            break;

                            case 0:
                                System.out.println("프로그램 종료");
                                return;


                                default:
                                    System.out.println("잘못된 입력입니다.");


            }

        }



    }


    public void inventoryRunForUser(UserDto userDto) {
        System.out.println("\n=== 회원 재고 조회 메뉴 ===");
        System.out.println("1. 내 회사 재고 조회");
        System.out.println("0. 종료");
        System.out.print("선택 > ");
        int choice = validCheck.inputNumRegex();

        switch (choice) {
            case 1 -> inventoryReadController.ReadByClientID(userDto);
            case 0 -> {
                System.out.println("프로그램 종료");
                return;
            }
            default -> System.out.println("잘못된 입력입니다.");







        }

    }


    // 관리자용 재고조회 메뉴 세분화
    private void inventoryReadMenu(AdminDto adminDto){
        System.out.println("\n--- 재고 조회 메뉴 ---");
        System.out.println("1. 상품명으로 조회");
        System.out.println("2. 카테고리별 조회");
        System.out.println("3. 입점 ID별 조회");
        System.out.println("4. 전체 조회");
        System.out.print("선택 > ");
        int choice = validCheck.inputNumRegex();

        switch (choice) {
            case 1:
                inventoryReadController.ReadByProductName();
                break;

                case 2:
                    inventoryReadController.ReadByCategory();
                    break;

                    case 3:
                        inventoryReadController.ReadByClientID();
                        break;

                        case 4:
                            inventoryReadController.ReadAll();
                            break;
        }


    }

}
