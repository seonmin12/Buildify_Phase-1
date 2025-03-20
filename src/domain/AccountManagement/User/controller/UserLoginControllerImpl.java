package domain.AccountManagement.User.controller;

import common.ValidCheck;
import domain.AccountManagement.User.service.UserLoginService;
import dto.UserDto;

/**
 * {@link UserLoginController}의 구현체.
 *
 * <p>사용자의 로그인, 로그아웃 및 현재 로그인한 사용자 정보를 제공하는 기능을 수행합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class UserLoginControllerImpl implements UserLoginController {
    private final ValidCheck validCheck;
    private final UserLoginService userLoginService;
    private static UserDto userDto;

    /**
     * {@code UserLoginControllerImpl} 생성자.
     *
     * @param validCheck 입력값 유효성 검사를 위한 유틸리티 클래스
     * @param userLoginService 사용자 로그인 및 로그아웃 관련 비즈니스 로직을 처리하는 서비스 클래스
     */
    public UserLoginControllerImpl(ValidCheck validCheck, UserLoginService userLoginService) {
        this.validCheck = validCheck;
        this.userLoginService = userLoginService;
    }

    /**
     * 사용자의 로그인을 수행합니다.
     *
     * <p>사용자로부터 ID와 비밀번호를 입력받고, {@link UserLoginService}를 이용하여 로그인 절차를 진행합니다.
     * 로그인 성공 시 사용자 정보를 저장하고, 실패 시 {@code false}를 반환합니다.</p>
     *
     * @return 로그인 성공 시 {@code true}, 실패 시 {@code false}
     */
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

    /**
     * 사용자의 로그아웃을 수행합니다.
     *
     * <p>현재 로그인된 사용자의 정보를 초기화하여 로그아웃을 처리합니다.</p>
     *
     * @return 로그아웃 성공 시 {@code true}
     */
    @Override
    public boolean logout() {
        userDto = null;
        System.out.println("로그아웃 되었습니다.");
        return true;
    }

    /**
     * 현재 로그인한 사용자 정보를 반환합니다.
     *
     * <p>로그인된 사용자가 없을 경우, {@code null}을 반환하며 "로그인 상태가 아닙니다."라는 메시지를 출력합니다.</p>
     *
     * @return 현재 로그인한 사용자의 {@link UserDto} 객체, 로그인 상태가 아닐 경우 {@code null}
     */
    @Override
    public UserDto getUserInfo() {
        if(userDto == null){
            System.out.println("로그인 상태가 아닙니다.");
            return null;
        }

        return userDto;
    }
}
