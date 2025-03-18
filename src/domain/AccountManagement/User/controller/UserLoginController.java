package domain.AccountManagement.User.controller;

import dto.UserDto;

public interface UserLoginController {
    boolean login();

    boolean logout();

    /**
     * 로그인 되어있는 유저 객체 호출
     * @return UserDto
     */
    UserDto getUserInfo();
}
