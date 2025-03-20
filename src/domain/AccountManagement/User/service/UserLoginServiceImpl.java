package domain.AccountManagement.User.service;

import domain.AccountManagement.User.repository.UserLoginRepository;
import dto.UserDto;

/**
 * {@link UserLoginService}의 구현체.
 *
 * <p>사용자의 로그인 인증을 수행하는 서비스 클래스입니다.</p>
 *
 * <p>이 클래스는 {@link UserLoginRepository}를 이용하여 데이터베이스에서 사용자 정보를 조회하고
 * 입력된 비밀번호와 비교하여 로그인 여부를 결정합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class UserLoginServiceImpl implements UserLoginService {
    private final UserLoginRepository userLoginRepository;

    /**
     * {@code UserLoginServiceImpl} 생성자.
     *
     * @param userLoginRepository 사용자 로그인 인증을 위한 리포지토리 객체
     */
    public UserLoginServiceImpl(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    /**
     * 사용자 로그인 인증을 수행합니다.
     *
     * <p>{@link UserLoginRepository}를 이용하여 데이터베이스에서 사용자 정보를 조회하고
     * 입력된 비밀번호와 비교하여 로그인 여부를 결정합니다.</p>
     *
     * @param userid 로그인할 사용자 ID
     * @param password 로그인할 사용자의 비밀번호
     * @return 로그인된 사용자의 {@link UserDto} 객체, 인증 실패 시 {@code null} 반환
     */
    @Override
    public UserDto login(String userid, String password) {
        return userLoginRepository.login(userid, password);
    }
}
