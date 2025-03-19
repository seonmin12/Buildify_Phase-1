package domain.Outbound.controller;

import common.ValidCheck;
import dto.UserDto;

import static common.ErrorCode.*;
import static common.Text.*;

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
    public void outboundAdmin() {
        while (true) {
            System.out.println(OUTBOUND_ADMIN_MENU.getText());
            System.out.println(OUTBOUND_ADMIN_SELECT.getText());
            int choice = validCheck.inputNumRegex();
            switch (choice) {
                case 1:
                    System.out.println(OUTBOUND_SELECT_MENU.getText());
                    choice = validCheck.inputNumRegex();
                    switch (choice) {
                        case 1 -> outboundAdminController.searchOutboundList();
                        case 2 -> outboundAdminController.searchOutboundByUser();
                    }
                    break;
                case 2:
                    System.out.println(OUTBOUND_APPROVE_MENU.getText());
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
                    System.out.println(ERROR_INPUT.getText());
                    return;
            }
        }
    }

    /**
     * 고객용 출고 메뉴 통합 컨트롤러 메소드입니다.
     */
    public void outboundUser(UserDto userDto) {
        System.out.println("1. 출고 요청 2. 출고 조회 3. 출고 요청 취소");
        int input = validCheck.inputNumRegex();
        switch (input) {
            case 1:
                outboundUserController.requestOutbound(userDto.getClient_id());
                break;
            case 2:
                outboundUserController.outboundUserRead(userDto.getClient_id());
                break;
            case 3:
                outboundUserController.outboundUserDelete(userDto.getClient_id());
                break;
            default:
                System.out.println(ERROR_INPUT.getText());
                break;
        }
    }
}
