package domain.AccountManagement.User.controller;

import common.ValidCheck;
import domain.AccountManagement.User.service.UserLoginService;
import dto.UserDto;

public class UserLoginControllerImpl implements UserLoginController {
    private final ValidCheck validCheck;
    private final UserLoginService userLoginService;
    private static UserDto userDto;

    public UserLoginControllerImpl(ValidCheck validCheck, UserLoginService userLoginService) {
        this.validCheck = validCheck;
        this.userLoginService = userLoginService;
    }

    @Override
    public boolean login() {
        System.out.println("로그인을 시도합니다.");

        System.out.println("ID를 입력하세요");
        String userID = validCheck.inputStringRegex(validCheck.ID_REGEX);
        System.out.println("비밀번호를 입력하세요.");
        String password = validCheck.inputStringRegex(validCheck.SIGN_UP_PASSWORD_REGEX);
        userDto = userLoginService.login(userID,password);

        if (userDto == null) {
            System.out.println("로그인에 실패 하였습니다.");
            return false;
        } else {
            System.out.println("로그인에 성공 하였습니다.");
            return true;
        }
    }

    @Override
    public boolean logout() {
        userDto = null;
        System.out.println("로그아웃 되었습니다.");
        return true;
    }

    @Override
    public UserDto getUserInfo() {
        if(userDto == null){
            System.out.println("로그인 상태가 아닙니다.");
            return null;
        }

        return userDto;
    }
}
