package domain.AccountManagement.User.repository;

import dto.UserDto;

/**
 * 회원가입 관련 데이터 처리를 담당하는 리포지토리 인터페이스.
 *
 * <p>이 인터페이스는 신규 사용자 등록 및 ID 중복 검사 기능을 정의합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public interface SignUpRepository {

    /**
     * 새로운 사용자를 데이터베이스에 추가합니다.
     *
     * @param userDto 등록할 사용자 정보를 포함한 {@link UserDto} 객체
     * @return 회원가입이 성공하면 {@code true}, 실패하면 {@code false}
     */
    boolean InsertUser(UserDto userDto);

    /**
     * 주어진 사용자 ID가 중복되는지 확인합니다.
     *
     * @param userid 중복 여부를 확인할 사용자 ID
     * @return 중복된 ID가 있으면 {@code true}, 없으면 {@code false}
     */
    boolean duplicateCheckUserID(String userid);
}
