package domain.AccountManagement.User.controller;

import common.ValidCheck;
import domain.AccountManagement.User.service.SignUpService;
import dto.UserDto;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * {@link SignUpController}의 구현체.
 *
 * <p>사용자의 회원가입 및 ID 중복 확인 기능을 제공합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class SignUpControllerImpl implements SignUpController {
    private final ValidCheck validCheck;
    private final SignUpService signUpService;

    /**
     * {@code SignUpControllerImpl} 생성자.
     *
     * @param validCheck 입력값 유효성 검사를 위한 유틸리티 클래스
     * @param signUpService 회원가입 관련 비즈니스 로직을 처리하는 서비스 클래스
     */
    public SignUpControllerImpl(ValidCheck validCheck, SignUpService signUpService) {
        this.validCheck = validCheck;
        this.signUpService = signUpService;
    }

    /**
     * 사용자의 회원가입을 수행합니다.
     *
     * <p>사용자는 ID, 비밀번호, 이름, 전화번호, 주소, 이메일, 사업자 번호, 창고 할당량 등의 정보를 입력해야 합니다.</p>
     * <p>비밀번호는 SHA-256 해시 알고리즘을 사용하여 암호화되며, 무작위 Salt 값이 추가됩니다.</p>
     *
     * @return 회원가입이 성공하면 {@code true}, 실패하면 {@code false}
     */
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

        // 🔥 랜덤 Salt 생성
        String salt = getSalt();

        // 🔥 비밀번호 + Salt 암호화 (SHA-256 적용)
        String encryptedPassword = getEncrypt(password, salt);

        // 🔥 비밀번호 + Salt를 하나의 문자열로 저장
        String passwordWithSalt = encryptedPassword + ":" + salt;

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
        System.out.println("창고 할당량을 입력하세요 ((정수 또는 소수점 이하 2자리까지 가능, 예: 42, 42.5, 100.99) 단위: 평방미터): ");
        BigDecimal size = validCheck.inputDecimalRegex(validCheck.PRODUCT_SIZE_REGEX);

        UserDto userDto = new UserDto(null, name, phone, email, address, businessNumber, null, id, passwordWithSalt,
                0, size, BigDecimal.valueOf(0));

        return signUpService.registerUser(userDto);
    }

    /**
     * 주어진 사용자 ID가 중복되는지 확인합니다.
     *
     * @param userid 중복 여부를 확인할 사용자 ID
     * @return 중복된 ID가 있으면 {@code true}, 없으면 {@code false}
     */
    @Override
    public boolean duplicateCheckUserID(String userid) {
        return signUpService.duplicateCheckUserID(userid);
    }

    /**
     * 무작위 Salt 값을 생성합니다.
     *
     * <p>비밀번호 암호화를 위한 16바이트 길이의 무작위 Salt를 생성하며, Base64로 인코딩됩니다.</p>
     *
     * @return 생성된 Salt 값 (Base64 인코딩 문자열)
     */
    public String getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];

        sr.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * 주어진 비밀번호에 Salt 값을 적용하여 SHA-256 해시 알고리즘으로 암호화합니다.
     *
     * <p>SHA-256 해싱 후 16진수 문자열로 변환하여 반환합니다.</p>
     *
     * @param pwd 사용자가 입력한 원본 비밀번호
     * @param salt 비밀번호 암호화를 위한 Salt 값
     * @return SHA-256으로 암호화된 비밀번호
     * @throws RuntimeException 암호화 알고리즘을 찾을 수 없는 경우 예외 발생
     */
    public String getEncrypt(String pwd, String salt) {
        String result= "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((pwd + salt).getBytes());
            byte[] pwdSalt = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : pwdSalt) {
                sb.append(String.format("%02x", b));
            }

            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
