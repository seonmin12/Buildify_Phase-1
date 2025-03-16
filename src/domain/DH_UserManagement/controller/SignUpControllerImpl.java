package domain.DH_UserManagement.controller;

import common.ValidCheck;
import domain.DH_UserManagement.repository.SignUpRepositoryImpl;
import domain.DH_UserManagement.service.SignUpService;
import dto.UserDto;

public class SignUpControllerImpl implements SignUpController {
    private final ValidCheck validCheck;
    private final SignUpService signUpService;

    public SignUpControllerImpl(ValidCheck validCheck, SignUpService signUpService) {
        this.validCheck = validCheck;
        this.signUpService = signUpService;
    }

    @Override
    public boolean signUp() {
        UserDto userDto = new UserDto();
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
