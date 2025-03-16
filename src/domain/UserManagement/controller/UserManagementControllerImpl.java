package domain.UserManagement.controller;

import common.ValidCheck;
import domain.UserManagement.service.UserManagementService;
import dto.AdminDto;
import dto.UserDto;

import java.util.List;

public class UserManagementControllerImpl implements UserManagementController{
    private final UserManagementService userManagementService;
    private final ValidCheck validCheck;

    public UserManagementControllerImpl(UserManagementService userManagementService, ValidCheck validCheck) {
        this.userManagementService = userManagementService;
        this.validCheck = validCheck;
    }

    @Override
    public void pendingApprovalUsers(AdminDto adminDto) {
        if ((adminDto == null) || (!adminDto.getAdminRole().equals("총관리자") && !adminDto.getAdminRole().equals("창고관리자"))){
            System.out.println("관리자만 접근 가능한 메뉴입니다.");
            return;
        }

        List<UserDto> pendingList = userManagementService.pendingApprovalUsers();
        System.out.println("회원ID 이름 연락처 이메일 주소 가입일 상태");
        for (UserDto users : pendingList) {
            System.out.printf("%s %s %s %s %s %s %d\n",
                    users.getClient_id(),users.getUser_name(),
                    users.getUser_phone(),users.getUser_email(),
                    users.getUser_adress(),users.getUser_enterday(),
                    users.getUser_status());
        }
    }

    @Override
    public void approveUser(AdminDto adminDto) {

        if ((adminDto == null) || (!adminDto.getAdminRole().equals("총관리자") && !adminDto.getAdminRole().equals("창고관리자"))){
            System.out.println("관리자만 접근 가능한 메뉴입니다.");
            return;
        }

        System.out.println("승인할 회원 ID를 입력하세요.");
        String client_id = validCheck.inputAnyString();
        userManagementService.approveUser(client_id);

    }

    @Override
    public void listAllUsers(AdminDto adminDto) {

        if ((adminDto == null) || (!adminDto.getAdminRole().equals("총관리자") && !adminDto.getAdminRole().equals("창고관리자"))){
            System.out.println("관리자만 접근 가능한 메뉴입니다.");
            return;
        }

        List<UserDto> userDtoList = userManagementService.listAllUsers();
        System.out.println("회원ID 이름 연락처 이메일 주소 가입일 상태");
        for (UserDto users : userDtoList) {
            System.out.printf("%s %s %s %s %s %s %d\n",
                    users.getClient_id(),users.getUser_name(),
                    users.getUser_phone(),users.getUser_email(),
                    users.getUser_adress(),users.getUser_enterday(),
                    users.getUser_status());
        }
    }

    @Override
    public void searchUser(AdminDto adminDto) {
        if ((adminDto == null) || (!adminDto.getAdminRole().equals("총관리자") && !adminDto.getAdminRole().equals("창고관리자"))){
            System.out.println("관리자만 접근 가능한 메뉴입니다.");
            return;
        }
        System.out.println("검색할 회원 Number를 입력해주세요.");
        System.out.printf("입력 : ");
        String client_id = validCheck.inputAnyString();
        UserDto userdto = userManagementService.searchUser(client_id);
        System.out.println("회원ID 이름 연락처 이메일 주소 가입일 상태");
            System.out.printf("%s %s %s %s %s %s %d\n",
                    userdto.getClient_id(),userdto.getUser_name(),
                    userdto.getUser_phone(),userdto.getUser_email(),
                    userdto.getUser_adress(),userdto.getUser_enterday(),
                    userdto.getUser_status());
    }

    @Override
    public void updateUser(AdminDto adminDto) {
        if ((adminDto == null) || (!adminDto.getAdminRole().equals("총관리자") && !adminDto.getAdminRole().equals("창고관리자"))){
            System.out.println("관리자만 접근 가능한 메뉴입니다.");
            return;
        }

        System.out.println("변경할 회원 ID를 입력하세여.");
        System.out.println("선택 : ");
        String client_id = validCheck.inputAnyString();
        System.out.println("변경할 회원정보를 선택하세요.");
        System.out.println("1.연락처 2.이메일 3.주소");
        System.out.println("선택 : ");
        int choice = validCheck.inputNumRegex();
        System.out.printf("변경할 정보를 입력하세요.");
        System.out.printf("선택 : ");
        String newValue = null;
        switch (choice){
            case 1 -> newValue = validCheck.inputStringRegex(validCheck.NUMBER_REGEX);
            case 2 -> newValue = validCheck.inputStringRegex(validCheck.EMAIL_REGEX);
            case 3 -> newValue = validCheck.inputAnyString();
        }
        userManagementService.updateUser(client_id,choice,newValue);
    }

    @Override
    public void updateSelfAdmin(AdminDto adminDto) {
        if ((adminDto == null) || (!adminDto.getAdminRole().equals("총관리자") && !adminDto.getAdminRole().equals("창고관리자"))){
            System.out.println("관리자만 접근 가능한 메뉴입니다.");
            return;
        }

        System.out.println("변경할 회원 ID를 입력하세여.");
        System.out.println("선택 : ");
        String admin_id = adminDto.getAdminNumber();
        System.out.println("변경할 관리자 정보를 선택하세요.");
        System.out.println("1.연락처  2.이메일  3.주소");
        System.out.println("선택 : ");
        int choice = validCheck.inputNumRegex();
        System.out.printf("변경할 정보를 입력하세요.");
        System.out.printf("선택 : ");
        String newValue = null;
        switch (choice){
            case 1 -> newValue = validCheck.inputStringRegex(validCheck.NUMBER_REGEX);
            case 2 -> newValue = validCheck.inputStringRegex(validCheck.EMAIL_REGEX);
            case 3 -> newValue = validCheck.inputAnyString();
        }
        userManagementService.updateSelfAdmin(admin_id,choice,newValue);
    }

    @Override
    public void updateAdmin(AdminDto adminDto) {
        if (adminDto == null || !adminDto.getAdminRole().equals("총관리자")){
            System.out.println("창고관리자 수정은 총관리자만 가능합니다.");
            return;
        }

        System.out.println("변경할 회원 ID를 입력하세여.");
        System.out.println("선택 : ");
        String admin_id = validCheck.inputAnyString();
        System.out.println("변경할 관리자 정보를 선택하세요.");
        System.out.println("1.연락처  2.이메일  3.주소");
        System.out.println("선택 : ");
        int choice = validCheck.inputNumRegex();
        System.out.printf("변경할 정보를 입력하세요.");
        System.out.printf("선택 : ");
        String newValue = null;
        switch (choice){
            case 1 -> newValue = validCheck.inputStringRegex(validCheck.NUMBER_REGEX);
            case 2 -> newValue = validCheck.inputStringRegex(validCheck.EMAIL_REGEX);
            case 3 -> newValue = validCheck.inputAnyString();
        }
        userManagementService.updateSelfAdmin(admin_id,choice,newValue);
    }

    @Override
    public void listAllLocalAdmin(AdminDto adminDto) {
        if (adminDto == null || !adminDto.getAdminRole().equals("총관리자")){
            System.out.println("창고관리자 조회는 총관리자만 가능합니다.");
            return;
        }
        List<AdminDto> adminDtoList = userManagementService.listAllLocalAdmin();
        for (AdminDto admins : adminDtoList){
            System.out.printf("%s %s %s %s %s %s %s\n",
                    admins.getAdminNumber(),admins.getAdminRole(),
                    admins.getAdminName(),admins.getAdminEmail(),
                    admins.getAdminEnterday(),admins.getAdminAddress(),admins.getAdminPhone());
        }
    }
}
