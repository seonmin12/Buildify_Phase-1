package domain.Outbound.controller;

import common.ValidCheck;

/**
 * Outbound 통합 컨트롤러입니다.
 */
public class OutboundController {
    private final ValidCheck validCheck;
    private final OutboundAdminController outboundAdminController;
    private final OutboundUserController outboundUserController;

    public OutboundController(ValidCheck validCheck, OutboundAdminController outboundAdminController, OutboundUserController outboundUserController) {
        this.validCheck = validCheck;
        this.outboundAdminController = outboundAdminController;
        this.outboundUserController = outboundUserController;
    }

    /**
     * 관리자용 출고 메뉴 통합 컨트롤러 메소드입니다.
     */
    public void outboundAdmin(){
        while (true) {
            System.out.println("출고관리 관리자 메뉴 입니다.");
            System.out.println("1.출고 리스트 조회 2. 승인 3. 이전 메뉴");
            int choice = validCheck.inputNumRegex();
            switch (choice) {
                case 1:
                    System.out.println("1.전체 조회 \t\t2.업체별 조회");
                    choice = validCheck.inputNumRegex();
                    switch (choice) {
                        case 1 -> outboundAdminController.searchOutboundList();
                        case 2 -> outboundAdminController.searchOutboundByUser();
                    }
                    break;
                case 2:
                    System.out.println("1. 전체 승인 2. 업체별 승인 3. 출고 아이디 승인 4. 반려(출고Number)");
                    choice = validCheck.inputNumRegex();
                    switch (choice) {
                        case 1 -> outboundAdminController.approveAllList();
                        case 2 -> outboundAdminController.approveById();
                        case 3 -> outboundAdminController.approveOneNumber();
                        case 4 -> outboundAdminController.returnOneNumber();
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("올바른 번호를 선택하세요.");
                    return;
            }
        }
    }

    /**
     * 고객용 출고 메뉴 통합 컨트롤러 메소드입니다.
     */
    public void outboundUser(){}
}
