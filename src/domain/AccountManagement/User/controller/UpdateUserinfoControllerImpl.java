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
                break;

            case 2:
                System.out.print("새로운 전화번호를 입력하세요: ");
                String newPhone = validCheck.inputAnyString();
                isSuccess = updateUserinfoService.updateUserinfo(userDto.getClient_id(), selected, newPhone);
                break;

            case 3:
                System.out.print("새로운 이메일을 입력하세요: ");
                String newEmail = validCheck.inputAnyString();
                isSuccess = updateUserinfoService.updateUserinfo(userDto.getClient_id(), selected, newEmail);
                break;

            case 4:
                System.out.print("새로운 주소를 입력하세요: ");
                String newAddress = validCheck.inputAnyString();
                isSuccess = updateUserinfoService.updateUserinfo(userDto.getClient_id(), selected, newAddress);
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
                    }
                } while (!password.equals(confirmPassword));
                break;

            default:
                System.out.println("❌ 잘못된 입력입니다. 1~5 사이의 숫자를 입력해주세요.");
                break;
        }

        return isSuccess;
    }

}
