package domain.DH_UserManagement.controller;

import dto.UserDto;

public interface UserIntegratedController {
    boolean userSignUp();
    boolean userLogin();
    UserDto getUserInfo();
    boolean userLogout();
    boolean requestProductRegist();
    boolean getAllProducts();
}
