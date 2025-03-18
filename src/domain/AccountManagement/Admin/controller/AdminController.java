package domain.AccountManagement.Admin.controller;

import common.ValidCheck;
import dto.AdminDto;

import static common.ErrorCode.ERROR_INPUT;
import static common.Text.*;

public class AdminController {
    private final ValidCheck validCheck;
    private final LoginController loginController;
    private final UserManagementController userManagementController;

    public AdminController(ValidCheck validCheck, LoginController loginController, UserManagementController userManagementController) {
        this.validCheck = validCheck;
        this.loginController = loginController;
        this.userManagementController = userManagementController;
    }

    public void adminUserManagement(AdminDto adminDto) {
        System.out.println(ADMIN_USER_MANAGEMENT_MENU.getText());
        int choice = validCheck.inputNumRegex();
        switch (choice){
            case 1:
                userManagementController.pendingApprovalUsers(adminDto);
                System.out.println(ADMIN_MENU_CHOICE.getText());
                choice = validCheck.inputNumRegex();
                switch (choice) {
                    case 1 -> userManagementController.approveUser(adminDto);
                    case 2 -> {
                        return;
                    }
                }
                break;
            case 2:
                System.out.println(ADMIN_USER_SERACH.getText());
                choice = validCheck.inputNumRegex();
                switch (choice) {
                    case 1 -> userManagementController.listAllUsers(adminDto);
                    case 2 -> userManagementController.searchUser(adminDto);
                }
                break;
            case 3:
                System.out.println(USER_INFO_CHANGER.getText());
                userManagementController.updateUser(adminDto);
                break;
            case 4:
                System.out.println("1. 내 정보 조회 2. 내 정보 수정");
                choice = validCheck.inputNumRegex();
                switch (choice) {
                    case 1 -> userManagementController.searchMyInfo(adminDto);
                    case 2 -> userManagementController.updateSelfAdmin(adminDto);
                    default -> System.out.println(ERROR_INPUT.getText());
                }

                break;
            case 5:
                System.out.println(LOCAL_ADMIN_MENU.getText());
                choice = validCheck.inputNumRegex();
                switch (choice){
                    case 1 -> userManagementController.listAllLocalAdmin(adminDto);
                    case 2 -> {
                        adminDto = loginController.getAdminLoginStatus();
                        userManagementController.updateAdmin(adminDto);
                    }
                    default -> System.out.println(ERROR_INPUT.getText());
                }
                break;
            default:
                System.out.println(ERROR_INPUT.getText());
                return;
        }
    }
}
