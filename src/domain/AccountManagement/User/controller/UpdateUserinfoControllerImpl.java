package domain.AccountManagement.User.controller;

import common.ValidCheck;
import domain.AccountManagement.User.service.UpdateUserinfoService;
import dto.UserDto;

/**
 * {@link UpdateUserinfoController}의 구현체.
 *
 * <p>사용자의 정보를 수정하고, 특정 사용자의 정보를 조회하는 기능을 제공합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class UpdateUserinfoControllerImpl implements UpdateUserinfoController {
    private final ValidCheck validCheck;
    private final UpdateUserinfoService updateUserinfoService;

    /**
     * {@code UpdateUserinfoControllerImpl} 생성자.
     *
     * @param validCheck 입력값 유효성 검사를 위한 유틸리티 클래스
     * @param updateUserinfoService 사용자 정보 업데이트 관련 비즈니스 로직을 처리하는 서비스 클래스
     */
    public UpdateUserinfoControllerImpl(ValidCheck validCheck, UpdateUserinfoService updateUserinfoService) {
        this.validCheck = validCheck;
        this.updateUserinfoService = updateUserinfoService;
    }

    /**
     * 사용자의 정보를 업데이트합니다.
     *
     * <p>사용자는 변경할 항목을 선택한 후 새로운 값을 입력해야 합니다.
     * 수정 가능한 항목은 이름, 전화번호, 이메일, 주소, 비밀번호입니다.</p>
     *
     * @param userDto 업데이트할 사용자 정보가 포함된 {@link UserDto} 객체
     * @return 업데이트가 성공하면 {@code true}, 실패하면 {@code false}
     */
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

    /**
     * 특정 사용자의 정보를 조회합니다.
     *
     * <p>사용자의 이름, 전화번호, 이메일, 주소, 사업자 번호, 창고 사용량 등의 정보를 출력합니다.</p>
     *
     * @param clientId 정보를 조회할 사용자 클라이언트 ID
     * @return 사용자 정보를 성공적으로 조회하면 {@code true}, 실패하면 {@code false}
     */
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
