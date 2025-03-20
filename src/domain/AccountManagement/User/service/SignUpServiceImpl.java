package domain.AccountManagement.User.service;

import domain.AccountManagement.User.repository.SignUpRepository;
import dto.UserDto;

/**
 * {@link SignUpService}의 구현체.
 *
 * <p>사용자의 회원가입 및 ID 중복 검사를 수행하는 서비스 클래스입니다.</p>
 *
 * <p>이 클래스는 {@link SignUpRepository}를 이용하여 데이터베이스와 연동됩니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class SignUpServiceImpl implements SignUpService {

    private final SignUpRepository signUpRepository;

    /**
     * {@code SignUpServiceImpl} 생성자.
     *
     * @param signUpRepository 사용자 등록 및 ID 중복 검사를 위한 리포지토리 객체
     */
    public SignUpServiceImpl(SignUpRepository signUpRepository) {
        this.signUpRepository = signUpRepository;
    }

    /**
     * 새로운 사용자를 등록합니다.
     *
     * <p>{@link SignUpRepository}를 이용하여 사용자 정보를 데이터베이스에 저장합니다.</p>
     *
     * @param userDto 등록할 사용자 정보를 포함한 {@link UserDto} 객체
     * @return 회원가입이 성공하면 {@code true}, 실패하면 {@code false}
     */
    @Override
    public boolean registerUser(UserDto userDto) {
        return signUpRepository.InsertUser(userDto);
    }

    /**
     * 주어진 사용자 ID가 중복되는지 확인합니다.
     *
     * <p>{@link SignUpRepository}를 이용하여 데이터베이스에서 해당 ID가 존재하는지 조회합니다.</p>
     *
     * @param userid 중복 여부를 확인할 사용자 ID
     * @return 중복된 ID가 있으면 {@code true}, 없으면 {@code false}
     */
    @Override
    public boolean duplicateCheckUserID(String userid) {
        return signUpRepository.duplicateCheckUserID(userid);
    }
}
