package domain.AccountManagement.User.controller;

import dto.UserDto;

/**
 * 사용자 로그인 및 로그아웃 기능을 제공하는 컨트롤러 인터페이스.
 *
 * <p>이 인터페이스는 사용자의 로그인, 로그아웃 및 현재 로그인한 사용자 정보를 조회하는 기능을 정의합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public interface UserLoginController {

    /**
     * 사용자의 로그인을 수행합니다.
     *
     * @return 로그인 성공 시 {@code true}, 실패 시 {@code false}
     */
    boolean login();

    /**
     * 사용자의 로그아웃을 수행합니다.
     *
     * @return 로그아웃 성공 시 {@code true}, 실패 시 {@code false}
     */
    boolean logout();

    /**
     * 현재 로그인한 사용자 정보를 가져옵니다.
     *
     * @return 현재 로그인한 사용자의 {@link UserDto} 객체
     */
    UserDto getUserInfo();
}
