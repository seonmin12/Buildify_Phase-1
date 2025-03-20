package domain.AccountManagement.User.controller;

import dto.UserDto;

/**
 * 사용자 정보 수정 기능을 제공하는 컨트롤러 인터페이스.
 *
 * <p>이 인터페이스는 사용자 정보 업데이트 및 업데이트할 사용자 정보를 조회하는 기능을 정의합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public interface UpdateUserinfoController {

    /**
     * 사용자 정보를 업데이트합니다.
     *
     * @param userDto 업데이트할 사용자 정보가 포함된 {@link UserDto} 객체
     * @return 업데이트가 성공하면 {@code true}, 실패하면 {@code false}
     */
    boolean updateUserinfo(UserDto userDto);

    /**
     * 특정 사용자의 정보를 조회하여 업데이트할 데이터를 가져옵니다.
     *
     * @param clientId 업데이트할 사용자의 클라이언트 ID
     * @return 사용자의 정보 조회가 성공하면 {@code true}, 실패하면 {@code false}
     */
    boolean getUpdateUserinfo(String clientId);
}
