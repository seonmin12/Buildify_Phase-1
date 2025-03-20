package domain.AccountManagement.User.service;

/**
 * {@link FindUseridService}의 구현체.
 *
 * <p>사용자의 사업자 번호를 이용하여 아이디 찾기 기능을 수행합니다.</p>
 *
 * <p>현재 구현에서는 실제 데이터 조회 없이 항상 빈 문자열({@code ""})을 반환하며,
 * 추후 데이터베이스 연동 후 기능이 추가될 예정입니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class FindUseridServiceImpl implements FindUseridService {

    /**
     * 사용자의 사업자 번호를 이용하여 아이디를 조회합니다.
     *
     * <p>현재 구현에서는 항상 빈 문자열({@code ""})을 반환하며,
     * 추후 데이터베이스 연동 후 기능이 추가될 예정입니다.</p>
     *
     * @param businessNumber 사용자의 사업자 등록 번호
     * @return 현재는 항상 빈 문자열({@code ""}) 반환 (추후 구현 예정)
     */
    @Override
    public String getUserIdByBusinessNumber(String businessNumber) {
        return "";
    }
}
