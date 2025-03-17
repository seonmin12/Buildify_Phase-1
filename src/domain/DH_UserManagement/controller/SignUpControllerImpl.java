package domain.DH_UserManagement.controller;

import common.ValidCheck;
import domain.DH_UserManagement.repository.SignUpRepositoryImpl;
import domain.DH_UserManagement.service.SignUpService;
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
        System.out.println("ID 입력: ");
        String id = validCheck.inputStringRegex(validCheck.ID_REGEX);
        boolean isduplicate = duplicateCheckUserID(id);

        if (isduplicate) {
            System.out.println("이미 사용된 ID 입니다.");
            return false;
        } else {
            System.out.println("사용 가능한 ID 입니다. ");
            //회원 가입 로직 이어서!
        }

        System.out.println("비밀번호를 입력하세요");
        String password = validCheck.inputStringRegex(validCheck.SIGN_UP_PASSWORD_REGEX);

        System.out.println("비밀번호를 다시 입력하세요");
        String confirmPassword = validCheck.inputStringRegex(validCheck.SIGN_UP_PASSWORD_REGEX);

        if(!password.equals(confirmPassword)) {
            System.out.println("비밀번호가 일치하지 않습니다.");
        }

        System.out.println("이름을 입력하세요.");
        String name = validCheck.inputStringRegex(validCheck.SIGN_UP_NAME_REGEX);
        System.out.println("전화번호를 입력하세요.");
        String phone = validCheck.inputStringRegex(validCheck.SIGN_UP_PHONE_REGEX);
        System.out.println("주소를 입력하세요.");
        String address = validCheck.inputStringRegex(validCheck.SIGN_UP_ADDRESS_REGEX);
        System.out.println("이메일 주소를 입력하세요.");
        String email = validCheck.inputStringRegex(validCheck.SIGN_UP_EMAIL_REGEX);
        System.out.println("사업자 번호를 입력하세요.");
        String businessNumber = validCheck.inputStringRegex(validCheck.SIGN_UP_BUSINESS_NUMBER_REGEX);
        System.out.println("창고 할당량을 입력하세요.");
        int warehouseSize = validCheck.inputNumRegex();

        UserDto userDto = new UserDto(null, name, phone, email, address, businessNumber, null, id, password, 0, BigDecimal.valueOf(warehouseSize), BigDecimal.valueOf(0));




        return signUpService.registerUser(userDto);
    }

    @Override
    public boolean duplicateCheckUserID(String userid) {
        return signUpService.duplicateCheckUserID(userid);
    }


    public static void main(String[] args) {
//        SignUpRepositoryImpl signUpRepository = new SignUpRepositoryImpl();
//
//        boolean isdup = signUpRepository.duplicateCheckUserID("zxcxzxz");

//        SignUpRepositoryImpl signUpRepository = new SignUpRepositoryImpl();
//
//        UserDto newUser = UserDto.builder()
//                .user_name("동키")
//                .user_phone("124124312")
//                .user_email("asdz@example.com")
//                .user_adress("서울특별시 강남구")
//                .business_number(987654321)
//                .user_id("hihihi")
//                .user_pw("password123!")
//                .user_status(0)
//                .user_ware_size(new java.math.BigDecimal("15.50"))
//                .user_ware_use(new java.math.BigDecimal("5.75"))
//                .build();
//
//        boolean isRegistered = signUpRepository.InsertUser(newUser);
//        System.out.println("isRegistered: " + isRegistered);
    }

}
