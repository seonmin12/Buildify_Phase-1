package domain.AccountManagement.User.repository;

import dto.UserDto;

/**
 * 사용자 로그인 기능을 담당하는 리포지토리 인터페이스.
 *
 * <p>이 인터페이스는 사용자의 로그인 기능을 정의하며,
 * 사용자 ID와 비밀번호를 검증하여 로그인된 사용자 정보를 반환합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public interface UserLoginRepository {

    /**
     * 사용자 로그인 인증을 수행합니다.
     *
     * <p>입력된 사용자 ID와 비밀번호를 검증하여 로그인된 사용자 정보를 반환합니다.</p>
     *
     * @param userid 로그인할 사용자 ID
     * @param password 로그인할 사용자의 비밀번호
     * @return 로그인된 사용자의 {@link UserDto} 객체, 인증 실패 시 {@code null} 반환
     */
    UserDto login(String userid, String password);
}
