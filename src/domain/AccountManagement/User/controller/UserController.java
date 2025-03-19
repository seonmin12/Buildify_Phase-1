package domain.AccountManagement.User.controller;

import dto.UserDto;

public class UserController {
    private final UserLoginController userLoginController;
    private final ProductController productController;
    private final SignUpController signUpController;
    private final UpdateUserinfoController updateUserinfoController;

    public UserController(UserLoginController userLoginController, ProductController productController, SignUpController signUpController, UpdateUserinfoController updateUserinfoController) {
        this.userLoginController = userLoginController;
        this.productController = productController;
        this.signUpController = signUpController;
        this.updateUserinfoController = updateUserinfoController;
    }

    public boolean userSignUp() {
        return signUpController.signUp();
    }

    public boolean userLogin() {
        return userLoginController.login();
    }

    public UserDto getUserInfo() {
        return userLoginController.getUserInfo();
    }

    public boolean userLogout() {
        return userLoginController.logout();
    }

    public boolean requestProductRegist() {
        return productController.requestProdcutRegist();
    }

    public boolean getAllProducts() {
        return productController.getAllProduct();
    }

    public boolean updateUserInfo() {
        return updateUserinfoController.updateUserinfo(userLoginController.getUserInfo());
    }

    public boolean getUpdateUserInfo(String clientId) {
        return updateUserinfoController.getUpdateUserinfo(clientId);
    }
}
