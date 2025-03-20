package domain.AccountManagement.User.controller;

/**
 * 비밀번호 찾기 기능을 제공하는 컨트롤러 인터페이스.
 *
 * <p>이 인터페이스는 사용자의 사업자 번호와 이메일을 이용하여 비밀번호 찾기 기능을 정의합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public interface FindPasswordController {

    /**
     * 사용자의 사업자 번호와 이메일을 기반으로 비밀번호 찾기를 수행합니다.
     *
     * @param businessNumber 사용자의 사업자 등록 번호
     * @param email 사용자의 이메일 주소
     * @return 비밀번호 찾기가 성공하면 {@code true}, 실패하면 {@code false}
     */
    boolean findPassword(String businessNumber, String email);
}
