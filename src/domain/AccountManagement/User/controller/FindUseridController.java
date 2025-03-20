package domain.AccountManagement.User.controller;

/**
 * 사용자 아이디 찾기 기능을 제공하는 컨트롤러 인터페이스.
 *
 * <p>이 인터페이스는 사용자의 사업자 번호를 이용하여 아이디를 찾는 기능을 정의합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public interface FindUseridController {

    /**
     * 사용자의 사업자 번호를 이용하여 아이디를 찾습니다.
     *
     * @param businessNumber 사용자의 사업자 등록 번호
     * @return 사용자의 아이디, 해당 사업자 번호가 없을 경우 {@code null} 반환 가능
     */
    String findUserIdByBusinessNumber(String businessNumber);
}
