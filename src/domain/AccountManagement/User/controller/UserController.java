package domain.AccountManagement.User.controller;

import dto.UserDto;

/**
 * 사용자 관리 기능을 제공하는 컨트롤러 클래스.
 *
 * <p>이 클래스는 회원가입, 로그인, 상품 등록, 사용자 정보 수정 등의 기능을 제공하며,
 * 각각의 기능은 해당하는 서브 컨트롤러를 통해 실행됩니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class UserController {
    private final UserLoginController userLoginController;
    private final ProductController productController;
    private final SignUpController signUpController;
    private final UpdateUserinfoController updateUserinfoController;

    /**
     * {@code UserController} 생성자.
     *
     * @param userLoginController 사용자 로그인 및 로그아웃 기능을 처리하는 컨트롤러
     * @param productController 상품 관련 기능을 처리하는 컨트롤러
     * @param signUpController 사용자 회원가입을 처리하는 컨트롤러
     * @param updateUserinfoController 사용자 정보 수정을 처리하는 컨트롤러
     */
    public UserController(UserLoginController userLoginController, ProductController productController,
                          SignUpController signUpController, UpdateUserinfoController updateUserinfoController) {
        this.userLoginController = userLoginController;
        this.productController = productController;
        this.signUpController = signUpController;
        this.updateUserinfoController = updateUserinfoController;
    }

    /**
     * 사용자 회원가입을 수행합니다.
     *
     * @return 회원가입이 성공하면 {@code true}, 실패하면 {@code false}
     */
    public boolean userSignUp() {
        return signUpController.signUp();
    }

    /**
     * 사용자의 로그인을 수행합니다.
     *
     * @return 로그인 성공 시 {@code true}, 실패 시 {@code false}
     */
    public boolean userLogin() {
        return userLoginController.login();
    }

    /**
     * 현재 로그인한 사용자의 정보를 가져옵니다.
     *
     * @return 로그인한 사용자의 {@link UserDto} 객체
     */
    public UserDto getUserInfo() {
        return userLoginController.getUserInfo();
    }

    /**
     * 사용자의 로그아웃을 수행합니다.
     *
     * @return 로그아웃 성공 시 {@code true}, 실패 시 {@code false}
     */
    public boolean userLogout() {
        return userLoginController.logout();
    }

    /**
     * 사용자가 상품 등록을 요청합니다.
     *
     * @return 상품 등록이 성공하면 {@code true}, 실패하면 {@code false}
     */
    public boolean requestProductRegist() {
        return productController.requestProdcutRegist();
    }

    /**
     * 모든 상품 목록을 조회합니다.
     *
     * @return 상품 목록 조회 성공 시 {@code true}, 실패 시 {@code false}
     */
    public boolean getAllProducts() {
        return productController.getAllProduct();
    }

    /**
     * 현재 로그인한 사용자의 정보를 수정합니다.
     *
     * @return 정보 수정이 성공하면 {@code true}, 실패하면 {@code false}
     */
    public boolean updateUserInfo() {
        return updateUserinfoController.updateUserinfo(userLoginController.getUserInfo());
    }

    /**
     * 특정 사용자의 정보를 조회하여 업데이트할 데이터를 가져옵니다.
     *
     * @param clientId 조회할 사용자의 클라이언트 ID
     * @return 사용자 정보 조회 성공 시 {@code true}, 실패 시 {@code false}
     */
    public boolean getUpdateUserInfo(String clientId) {
        return updateUserinfoController.getUpdateUserinfo(clientId);
    }
}
