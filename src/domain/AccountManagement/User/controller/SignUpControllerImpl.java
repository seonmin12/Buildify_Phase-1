package domain.AccountManagement.User.controller;

import common.ValidCheck;
import domain.AccountManagement.User.service.SignUpService;
import dto.UserDto;

import java.math.BigDecimal;

public class SignUpControllerImpl implements SignUpController {
    private final ValidCheck validCheck;
    private final SignUpService signUpService;

    public SignUpControllerImpl(ValidCheck validCheck, SignUpService signUpService) {
        this.validCheck = validCheck;
        this.signUpService = signUpService;
    }

    @Override
    public boolean signUp() {

        System.out.println("회원 가입을 시작합니다. ");

        String id;
        boolean isDuplicate;

        do {
            System.out.println("ID를 입력하세요 (영문자와 숫자 조합, 최소 4자 이상): ");
            id = validCheck.inputStringRegex(validCheck.ID_REGEX);
            isDuplicate = duplicateCheckUserID(id);

            if (isDuplicate) {
                System.out.println("❌ 이미 사용된 ID입니다. 다른 ID를 입력해주세요.");
            }
        } while (isDuplicate);

        System.out.println("✅ 사용 가능한 ID입니다.");

        String password;
        String confirmPassword;

        do {
            System.out.println("비밀번호를 입력하세요 (영문, 숫자, 특수문자 포함 8자 이상): ");
            password = validCheck.inputStringRegex(validCheck.SIGN_UP_PASSWORD_REGEX);

            System.out.println("비밀번호를 다시 입력하세요 (동일한 비밀번호를 입력해주세요): ");
            confirmPassword = validCheck.inputStringRegex(validCheck.SIGN_UP_PASSWORD_REGEX);

            if (!password.equals(confirmPassword)) {
                System.out.println("❌ 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
            }
        } while (!password.equals(confirmPassword));

        System.out.println("이름을 입력하세요 (한글 또는 영문, 최대 20자): ");
        String name = validCheck.inputStringRegex(validCheck.SIGN_UP_NAME_REGEX);
        System.out.println("전화번호를 입력하세요 (예: 010-1234-5678): ");
        String phone = validCheck.inputStringRegex(validCheck.SIGN_UP_PHONE_REGEX);
        System.out.println("주소를 입력하세요 (도로명 주소 또는 지번 주소 입력 가능): ");
        String address = validCheck.inputStringRegex(validCheck.SIGN_UP_ADDRESS_REGEX);
        System.out.println("이메일 주소를 입력하세요 (예: example@email.com): ");
        String email = validCheck.inputStringRegex(validCheck.SIGN_UP_EMAIL_REGEX);
        System.out.println("사업자 번호를 입력하세요 (숫자 10자리, 예: 123-45-67890): ");
        String businessNumber = validCheck.inputStringRegex(validCheck.SIGN_UP_BUSINESS_NUMBER_REGEX);
        System.out.println("창고 할당량을 입력하세요 (숫자 입력, 단위: 평방미터): ");
        int warehouseSize = validCheck.inputNumRegex();

        UserDto userDto = new UserDto(null, name, phone, email, address, businessNumber, null, id, password, 0, BigDecimal.valueOf(warehouseSize), BigDecimal.valueOf(0));

        return signUpService.registerUser(userDto);
    }

    @Override
    public boolean duplicateCheckUserID(String userid) {
        return signUpService.duplicateCheckUserID(userid);
    }

}
