package domain.AccountManagement.User.controller;

/**
 * {@link FindUseridController}의 구현체.
 *
 * <p>사용자의 사업자 번호를 기반으로 아이디를 찾는 기능을 수행합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class FindUseridControllerImpl implements FindUseridController {

    /**
     * 사용자의 사업자 번호를 이용하여 아이디를 찾습니다.
     *
     * <p>현재 구현에서는 비즈니스 로직이 없으며, 빈 문자열({@code ""})을 반환합니다.</p>
     *
     * @param businessNumber 사용자의 사업자 등록 번호
     * @return 사용자의 아이디, 현재는 항상 빈 문자열({@code ""}) 반환
     */
    @Override
    public String findUserIdByBusinessNumber(String businessNumber) {
        return "";
    }
}
