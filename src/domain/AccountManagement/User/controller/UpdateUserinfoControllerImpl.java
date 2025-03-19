package domain.AccountManagement.User.controller;

import common.ValidCheck;
import domain.AccountManagement.User.service.UpdateUserinfoService;
import dto.UserDto;

public class UpdateUserinfoControllerImpl implements UpdateUserinfoController {
    private final ValidCheck validCheck;
    private final UpdateUserinfoService updateUserinfoService;

    public UpdateUserinfoControllerImpl(ValidCheck validCheck, UpdateUserinfoService updateUserinfoService) {
        this.validCheck = validCheck;
        this.updateUserinfoService = updateUserinfoService;
    }

    @Override
    public boolean updateUserinfo(UserDto userDto) {
        boolean isSuccess = false;
        System.out.println("변경할 항목을 선택하세요:");
        System.out.println("1. 이름 변경");
        System.out.println("2. 전화번호 변경");
        System.out.println("3. 이메일 변경");
        System.out.println("4. 주소 변경");
        System.out.println("5. 비밀번호 변경");

        int selected = validCheck.inputNumRegex();

        switch (selected) {
            case 1:
                System.out.print("새로운 이름을 입력하세요: ");
                String newName = validCheck.inputAnyString();
                isSuccess = updateUserinfoService.updateUserinfo(userDto.getClient_id(), selected, newName);
                if (isSuccess) {
                    System.out.println(newName + " 으로 변경 되었습니다.");
                }
                break;

            case 2:
                System.out.print("새로운 전화번호를 입력하세요: ");
                String newPhone = validCheck.inputAnyString();
                isSuccess = updateUserinfoService.updateUserinfo(userDto.getClient_id(), selected, newPhone);
                if (isSuccess) {
                    System.out.println(newPhone + " 으로 변경 되었습니다.");
                }
                break;

            case 3:
                System.out.print("새로운 이메일을 입력하세요: ");
                String newEmail = validCheck.inputAnyString();
                isSuccess = updateUserinfoService.updateUserinfo(userDto.getClient_id(), selected, newEmail);
                if (isSuccess) {
                    System.out.println(newEmail + " 으로 변경 되었습니다.");
                }
                break;

            case 4:
                System.out.print("새로운 주소를 입력하세요: ");
                String newAddress = validCheck.inputAnyString();
                isSuccess = updateUserinfoService.updateUserinfo(userDto.getClient_id(), selected, newAddress);
                if (isSuccess) {
                    System.out.println(newAddress + " 으로 변경 되었습니다.");
                }
                break;

            case 5:
                String password, confirmPassword;
                do {
                    System.out.println("비밀번호를 입력하세요 (영문, 숫자, 특수문자 포함 8자 이상): ");
                    password = validCheck.inputStringRegex(validCheck.SIGN_UP_PASSWORD_REGEX);

                    System.out.println("비밀번호를 다시 입력하세요 (동일한 비밀번호를 입력해주세요): ");
                    confirmPassword = validCheck.inputStringRegex(validCheck.SIGN_UP_PASSWORD_REGEX);

                    if (!password.equals(confirmPassword)) {
                        System.out.println("❌ 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
                    } else {
                        isSuccess = updateUserinfoService.updateUserinfo(userDto.getClient_id(), selected, confirmPassword);
                        if (isSuccess) {
                            System.out.println("비밀번호가 변경 되었습니다.");
                        }
                    }
                } while (!password.equals(confirmPassword));
                break;

            default:
                System.out.println("❌ 잘못된 입력입니다. 1~5 사이의 숫자를 입력해주세요.");
                break;
        }

        return isSuccess;
    }

    @Override
    public boolean getUpdateUserinfo(String clientId) {
        boolean isSuccess = false;
        UserDto userDto = updateUserinfoService.getUpdateUserinfo(clientId);

        if (userDto != null) {
            isSuccess = true;
        }

        System.out.println("이름 : "  + userDto.getUser_name());
        System.out.println("전화번호 : "  + userDto.getUser_phone());
        System.out.println("이메일 : "  + userDto.getUser_email());
        System.out.println("주소 : "  + userDto.getUser_adress());
        System.out.println("사업자 번호 : "  + userDto.getBusiness_number());
        System.out.println("할당된 창고 양 : "  + userDto.getUser_ware_size());
        System.out.println("사용중인 창고 양 : "  + userDto.getUser_ware_use());

        return isSuccess;
    }

}
