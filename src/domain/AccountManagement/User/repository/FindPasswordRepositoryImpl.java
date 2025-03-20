package domain.AccountManagement.User.repository;

/**
 * {@link FindPasswordRepository}의 구현체.
 *
 * <p>사용자의 사업자 번호와 이메일을 이용하여 비밀번호 찾기 기능을 수행합니다.</p>
 *
 * <p>현재 구현에서는 실제 비밀번호 찾기 로직이 적용되지 않았으며, 항상 {@code false}를 반환합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class FindPasswordRepositoryImpl implements FindPasswordRepository {

    /**
     * 사용자의 사업자 번호와 이메일을 이용하여 비밀번호 찾기를 수행합니다.
     *
     * <p>현재 구현에서는 항상 {@code false}를 반환하며, 추후 데이터베이스 연동 후 구현될 예정입니다.</p>
     *
     * @param businessNumber 사용자의 사업자 등록 번호
     * @param email 사용자의 이메일 주소
     * @return 현재는 항상 {@code false} 반환 (추후 구현 예정)
     */
    @Override
    public boolean findPassword(String businessNumber, String email) {
        return false;
    }
}
