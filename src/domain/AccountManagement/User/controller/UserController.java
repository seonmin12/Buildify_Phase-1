package domain.AccountManagement.User.controller;

import dto.UserDto;

public class UserController {
    private final UserLoginController userLoginController;
    private final ProductController productController;
    private final SignUpController signUpController;

    public UserController(UserLoginController userLoginController, ProductController productController, SignUpController signUpController) {
        this.userLoginController = userLoginController;
        this.productController = productController;
        this.signUpController = signUpController;
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

    public boolean userLogout() { return userLoginController.logout(); }

    public boolean requestProductRegist() {
        return productController.requestProdcutRegist();
    }

    public boolean getAllProducts() {
        return productController.getAllProduct();
    }
}
