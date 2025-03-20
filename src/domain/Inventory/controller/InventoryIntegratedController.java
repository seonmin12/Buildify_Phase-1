package domain.Inventory.controller;

import common.ValidCheck;
import dto.AdminDto;
import dto.UserDto;
/**
 * 재고 통합 기능관리를 담당하는 컨트롤러 클래스.
 * <p>
 * 관리자 및 회원 권한에 따라 재고 조회, 수정, 삭제 기능을 제공한다.
 */
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



    /**
     * 관리자용 재고 관리 메뉴를 실행한다.
     * <p>
     * 재고 조회, 삭제, 수정 기능을 선택할 수 있다.
     *
     * @param adminDto 관리자 정보
     */
    public void inventoryRunForAdmin(AdminDto adminDto) {

        while(true){
            System.out.println("\n=== 재고 통합 관리 메뉴 ===");
            System.out.println("1. 재고 조회");
            System.out.println("2. 재고 삭제");
            System.out.println("3. 재고 수정");
            System.out.println("0. 이전 메뉴");
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

    /**
     * 회원 전용 재고 조회 메뉴를 실행한다.
     *
     * @param userDto 회원 정보
     */
    public void inventoryRunForUser(UserDto userDto) {
        System.out.println("\n=== 회원 재고 조회 메뉴 ===");
        System.out.println("1. 내 회사 재고 조회");
        System.out.println("0. 이전 메뉴");
        System.out.print("선택 > ");
        int choice = validCheck.inputNumRegex();

        switch (choice) {
            case 1 -> inventoryReadController.ReadByClientID(userDto);
            case 0 -> {

                return;
            }
            default -> System.out.println("잘못된 입력입니다.");







        }

    }


    /**
     * 관리자용 재고 조회 메뉴를 출력하고 기능을 실행한다.
     *
     * @param adminDto 관리자 정보
     */
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
