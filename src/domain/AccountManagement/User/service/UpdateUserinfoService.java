package domain.AccountManagement.User.service;

import dto.UserDto;

/**
 * 사용자 정보 업데이트 관련 비즈니스 로직을 제공하는 서비스 인터페이스.
 *
 * <p>이 인터페이스는 특정 사용자의 정보를 수정하고, 업데이트된 사용자 정보를 조회하는 기능을 정의합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public interface UpdateUserinfoService {

    /**
     * 특정 사용자의 정보를 업데이트합니다.
     *
     * <p>업데이트할 항목을 지정하고 새로운 값을 적용합니다.</p>
     *
     * @param clientId 정보를 업데이트할 사용자 클라이언트 ID
     * @param updateOption 업데이트할 항목의 옵션 (예: 1 - 이름, 2 - 전화번호, 3 - 이메일 등)
     * @param newValue 업데이트할 새로운 값
     * @return 업데이트가 성공하면 {@code true}, 실패하면 {@code false}
     */
    boolean updateUserinfo(String clientId, int updateOption, String newValue);

    /**
     * 특정 사용자의 최신 정보를 조회합니다.
     *
     * @param clientId 조회할 사용자 클라이언트 ID
     * @return 업데이트된 사용자 정보가 포함된 {@link UserDto} 객체, 사용자가 존재하지 않으면 {@code null}
     */
    UserDto getUpdateUserinfo(String clientId);
}
