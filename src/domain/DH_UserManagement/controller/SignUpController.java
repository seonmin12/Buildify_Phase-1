package domain.DH_UserManagement.controller;

import dto.UserDto;

public interface SignUpController {

    //회원가입
    boolean signUp();

    //중복된 ID 있는지 체크
    boolean duplicateCheckUserID(String userid);
}
