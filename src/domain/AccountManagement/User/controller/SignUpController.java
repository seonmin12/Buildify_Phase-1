package domain.AccountManagement.User.controller;

/**
 * 사용자 회원가입 관련 기능을 제공하는 컨트롤러 인터페이스.
 *
 * <p>이 인터페이스는 회원가입 및 ID 중복 검사 기능을 정의합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public interface SignUpController {

    /**
     * 사용자 회원가입을 수행합니다.
     *
     * <p>사용자로부터 필요한 정보를 입력받아 회원가입 절차를 진행합니다.</p>
     *
     * @return 회원가입이 성공하면 {@code true}, 실패하면 {@code false}
     */
    boolean signUp();

    /**
     * 주어진 사용자 ID가 중복되는지 확인합니다.
     *
     * @param userid 중복 여부를 확인할 사용자 ID
     * @return 중복된 ID가 있으면 {@code true}, 없으면 {@code false}
     */
    boolean duplicateCheckUserID(String userid);
}
