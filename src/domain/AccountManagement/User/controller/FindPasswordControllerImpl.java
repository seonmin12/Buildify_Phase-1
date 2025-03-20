package domain.AccountManagement.User.controller;

/**
 * {@link FindPasswordController}의 구현체.
 *
 * <p>사용자의 사업자 번호와 이메일을 기반으로 비밀번호 찾기 기능을 수행합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class FindPasswordControllerImpl implements FindPasswordController {

    /**
     * 사용자의 사업자 번호와 이메일을 이용하여 비밀번호 찾기를 수행합니다.
     *
     * <p>현재 구현에서는 비밀번호 찾기 기능이 아직 구현되지 않았으며, 항상 {@code false}를 반환합니다.</p>
     *
     * @param businessNumber 사용자의 사업자 등록 번호
     * @param email 사용자의 이메일 주소
     * @return 비밀번호 찾기가 성공하면 {@code true}, 실패하면 {@code false} (현재는 항상 {@code false} 반환)
     */
    @Override
    public boolean findPassword(String businessNumber, String email) {
        return false;
    }
}
