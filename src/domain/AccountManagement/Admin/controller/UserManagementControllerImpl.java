package domain.AccountManagement.Admin.controller;

import common.ValidCheck;
import domain.AccountManagement.Admin.service.UserManagementService;
import dto.AdminDto;
import dto.UserDto;

import java.util.List;

import static common.Text.*;

public class UserManagementControllerImpl implements UserManagementController{
    private final UserManagementService userManagementService;
    private final ValidCheck validCheck;

    public UserManagementControllerImpl(UserManagementService userManagementService, ValidCheck validCheck) {
        this.userManagementService = userManagementService;
        this.validCheck = validCheck;
    }

    /**
     * (관리자메뉴)승인 대기 회원 조회 메소드입니다.
     * @param adminDto
     */
    @Override
    public void pendingApprovalUsers(AdminDto adminDto) {
        if ((adminDto == null) || (!adminDto.getAdminRole().equals(SUPER_ADMIN.getText())
                && !adminDto.getAdminRole().equals(LOCAL_ADMIN.getText()))){
            System.out.println(ACCESS_ADMIN.getText());
            return;
        }

        List<UserDto> pendingList = userManagementService.pendingApprovalUsers();
        System.out.printf(PENDING_USER_LIST.getText());
        for (UserDto users : pendingList) {
            System.out.printf("%-10s %-8s %-15s %-25s %-20s %-12s %-10.2f\n",
                    users.getClient_id(),users.getUser_name(),
                    users.getUser_phone(),users.getUser_email(),
                    users.getUser_adress(),users.getUser_enterday(),
                    users.getUser_ware_size());
        }
        int size = userManagementService.getUseWareSize();
        System.out.println(AVAILABLE_WAREHOUSE.getText() + size);
    }

    /**
     * (관리자메뉴)승인 대기 회원 승인 메소드입니다.
     * @param adminDto
     */
    @Override
    public void approveUser(AdminDto adminDto) {

        if ((adminDto == null) || (!adminDto.getAdminRole().equals(SUPER_ADMIN.getText())
                && !adminDto.getAdminRole().equals(LOCAL_ADMIN.getText()))){
            System.out.println(ACCESS_ADMIN.getText());
            return;
        }

        System.out.println(ACCESS_USER_INPUT.getText());
        String client_id = validCheck.inputAnyString();
        userManagementService.approveUser(client_id);

    }

    /**
     * (관리자메뉴)전체 회원 조회 메소드입니다.
     * @param adminDto
     */
    @Override
    public void listAllUsers(AdminDto adminDto) {

        if ((adminDto == null) || (!adminDto.getAdminRole().equals(SUPER_ADMIN.getText())
                && !adminDto.getAdminRole().equals(LOCAL_ADMIN.getText()))){
            System.out.println(ACCESS_ADMIN.getText());
            return;
        }

        List<UserDto> userDtoList = userManagementService.listAllUsers();
        System.out.printf(USER_LIST.getText());
        for (UserDto users : userDtoList) {
            String status;
            if (users.getUser_status() == 0 ){
                status = "대기";
            }else status = "승인";
            System.out.printf("%-8s %-6s %-15s %-25s %-18s %-12s %-4s\n",
                    users.getClient_id(), users.getUser_name(),
                    users.getUser_phone(), users.getUser_email(),
                    users.getUser_adress(), users.getUser_enterday(),
                    status);
        }
    }

    /**
     * (관리자메뉴)회원 1명 검색 메소드입니다.
     * @param adminDto
     */
    @Override
    public void searchUser(AdminDto adminDto) {
        if ((adminDto == null) || (!adminDto.getAdminRole().equals(SUPER_ADMIN.getText())
                && !adminDto.getAdminRole().equals(LOCAL_ADMIN.getText()))){
            System.out.println(ACCESS_ADMIN.getText());
            return;
        }
        System.out.println(SEARCH_USER_INPUT.getText());
        System.out.printf(INPUT_CHOICE.getText());
        String client_id = validCheck.inputAnyString();
        UserDto userdto = userManagementService.searchUser(client_id);
        if (userdto == null){
            System.out.println(client_id + NOT_FIND_USER.getText());
            return;
        }
        System.out.printf("%-10s %-10s %-15s %-25s %-20s %-12s %-6s\n",
                "회원ID", "이름", "연락처", "이메일", "주소", "가입일", "상태");
        System.out.println(ROUND_BAR.getText());
        String status = (userdto.getUser_status() == 0) ? "대기" : "승인";
        System.out.printf("%-10s %-10s %-15s %-25s %-20s %-12s %-6s\n",
                userdto.getClient_id(), userdto.getUser_name(),
                userdto.getUser_phone(), userdto.getUser_email(),
                userdto.getUser_adress(), userdto.getUser_enterday(),
                status);
    }

    /**
     * (관리자메뉴)회원정보수정 메소드입니다.
     * @param adminDto
     */
    @Override
    public void updateUser(AdminDto adminDto) {
        if ((adminDto == null) || (!adminDto.getAdminRole().equals(SUPER_ADMIN.getText())
                && !adminDto.getAdminRole().equals(LOCAL_ADMIN.getText()))){
            System.out.println(ACCESS_ADMIN.getText());
            return;
        }

        System.out.println(SEARCH_USER_INPUT.getText());
        System.out.println(INPUT_CHOICE.getText());
        String client_id = validCheck.inputAnyString();
        UserDto userdto = userManagementService.searchUser(client_id);
        if (userdto == null){
            System.out.println(client_id + NOT_FIND_USER.getText());
            return;
        }
        System.out.println(CHOICE_INPUT_INFO.getText());
        System.out.println(SELECT_UPDATE.getText());
        System.out.println(INPUT_CHOICE.getText());
        int choice = validCheck.inputNumRegex();
        System.out.printf(INPUT_NEW_VALUE.getText());
        System.out.printf(INPUT_CHOICE.getText());
        String newValue = null;
        switch (choice){
            case 1 -> newValue = validCheck.inputStringRegex(validCheck.NUMBER_REGEX);
            case 2 -> newValue = validCheck.inputStringRegex(validCheck.EMAIL_REGEX);
            case 3 -> newValue = validCheck.inputAnyString();
        }
        userManagementService.updateUser(client_id,choice,newValue);
    }

    /**
     * (관리자메뉴)관리자 본인 정보 수정 메소드입니다.
     * @param adminDto
     */
    @Override
    public void updateSelfAdmin(AdminDto adminDto) {
        if ((adminDto == null) || (!adminDto.getAdminRole().equals(SUPER_ADMIN.getText())
                && !adminDto.getAdminRole().equals(LOCAL_ADMIN.getText()))){
            System.out.println(ACCESS_ADMIN.getText());
            return;
        }

        System.out.println(SEARCH_USER_INPUT.getText());
        System.out.println(INPUT_CHOICE.getText());
        String admin_id = adminDto.getAdminNumber();
        System.out.println(CHOICE_INPUT_INFO.getText());
        System.out.println(SELECT_UPDATE.getText());
        System.out.println(INPUT_CHOICE.getText());
        int choice = validCheck.inputNumRegex();
        System.out.printf(INPUT_NEW_VALUE.getText());
        System.out.printf(INPUT_CHOICE.getText());
        String newValue = null;
        switch (choice){
            case 1 -> newValue = validCheck.inputStringRegex(validCheck.NUMBER_REGEX);
            case 2 -> newValue = validCheck.inputStringRegex(validCheck.EMAIL_REGEX);
            case 3 -> newValue = validCheck.inputAnyString();
        }
        userManagementService.updateSelfAdmin(admin_id,choice,newValue);
    }

    /**
     * (총관리자 전용 메뉴)창고관리자 정보수정 메소드입니다.
     * @param adminDto
     */
    @Override
    public void updateAdmin(AdminDto adminDto) {
        if (adminDto == null || !adminDto.getAdminRole().equals(SUPER_ADMIN.getText())){
            System.out.println(ACCESS_SUPER_ADMIN.getText());
            return;
        }

        System.out.println(SEARCH_ADMIN_INPUT.getText());
        System.out.println(INPUT_CHOICE.getText());
        String admin_id = validCheck.inputAnyString();
        System.out.println(CHOICE_INPUT_INFO.getText());
        System.out.println(SELECT_UPDATE.getText());
        System.out.println(INPUT_CHOICE.getText());
        int choice = validCheck.inputNumRegex();
        System.out.println(INPUT_NEW_VALUE.getText());
        System.out.printf(INPUT_CHOICE.getText());
        String newValue = null;
        switch (choice){
            case 1 -> newValue = validCheck.inputStringRegex(validCheck.SIGN_UP_PHONE_REGEX);
            case 2 -> newValue = validCheck.inputStringRegex(validCheck.EMAIL_REGEX);
            case 3 -> newValue = validCheck.inputAnyString();
        }
        userManagementService.updateSelfAdmin(admin_id,choice,newValue);
    }

    /**
     * (총관리자 전용 메뉴)창고관리자 조회 메소드입니다.
     * @param adminDto
     */
    @Override
    public void listAllLocalAdmin(AdminDto adminDto) {
        if (adminDto == null || !adminDto.getAdminRole().equals(SUPER_ADMIN.getText())){
            System.out.println(ACCESS_SUPER_ADMIN.getText());
            return;
        }
        List<AdminDto> adminDtoList = userManagementService.listAllLocalAdmin();
        System.out.printf(ADMIN_LIST_MENU.getText());
        System.out.println(ROUND_BAR.getText());
        for (AdminDto admins : adminDtoList){
            System.out.printf("%-12s %-10s %-8s %-25s %-12s %-20s %-15s\n",
                    admins.getAdminNumber(),admins.getAdminRole(),
                    admins.getAdminName(),admins.getAdminEmail(),
                    admins.getAdminEnterday(),admins.getAdminAddress(),admins.getAdminPhone());
        }
    }

    /**
     * (관리자 메뉴) 내 정보 조회 메소드입니다.
     * @param admindto
     */
    @Override
    public void searchMyInfo(AdminDto admindto) {
        AdminDto searchAdmin = userManagementService.searchAdmin(admindto.getAdminNumber());
        System.out.printf(ADMIN_LIST_MENU.getText());
        System.out.println(ROUND_BAR.getText());
        System.out.printf("%-12s %-10s %-8s %-25s %-12s %-20s %-15s\n",
                searchAdmin.getAdminNumber(),searchAdmin.getAdminRole(),
                searchAdmin.getAdminName(),searchAdmin.getAdminEmail(),
                searchAdmin.getAdminEnterday(),searchAdmin.getAdminAddress(),searchAdmin.getAdminPhone());
        }
    }