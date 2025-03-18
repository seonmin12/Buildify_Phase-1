package domain.DH_UserManagement.controller;

import dto.UserDto;

public class UserIntegratedControllerImpl implements UserIntegratedController {
    private final UserLoginController userLoginController;
    private final ProductController productController;
    private final SignUpController signUpController;

    public UserIntegratedControllerImpl(UserLoginController userLoginController, ProductController productController, SignUpController signUpController) {
        this.userLoginController = userLoginController;
        this.productController = productController;
        this.signUpController = signUpController;
    }

    @Override
    public boolean userSignUp() {
        return signUpController.signUp();
    }

    @Override
    public boolean userLogin() {
        return userLoginController.login();
    }

    @Override
    public UserDto getUserInfo() {
        return userLoginController.getUserInfo();
    }

    @Override
    public boolean userLogout() {
        return userLoginController.logout();

    }

    @Override
    public boolean requestProductRegist() {
        return productController.requestProdcutRegist();
    }

    @Override
    public boolean getAllProducts() {
        return productController.getAllProduct();
    }
}
