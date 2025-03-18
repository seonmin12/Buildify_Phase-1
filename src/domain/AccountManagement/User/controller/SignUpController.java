package domain.AccountManagement.User.controller;

public interface SignUpController {

    //회원가입
    boolean signUp();

    //중복된 ID 있는지 체크
    boolean duplicateCheckUserID(String userid);
}
