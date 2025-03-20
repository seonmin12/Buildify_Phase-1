package domain.AccountManagement.User.service;

import domain.AccountManagement.User.repository.UpdateUserinfoRepository;
import dto.UserDto;

/**
 * {@link UpdateUserinfoService}의 구현체.
 *
 * <p>사용자의 정보를 업데이트하고 최신 정보를 조회하는 기능을 수행하는 서비스 클래스입니다.</p>
 *
 * <p>이 클래스는 {@link UpdateUserinfoRepository}를 이용하여 데이터베이스와의 연동을 처리합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class UpdateUserinfoServiceImpl implements UpdateUserinfoService {

    private final UpdateUserinfoRepository updateUserinfoRepository;

    /**
     * {@code UpdateUserinfoServiceImpl} 생성자.
     *
     * @param updateUserinfoRepository 사용자 정보 업데이트 및 조회를 위한 리포지토리 객체
     */
    public UpdateUserinfoServiceImpl(UpdateUserinfoRepository updateUserinfoRepository) {
        this.updateUserinfoRepository = updateUserinfoRepository;
    }

    /**
     * 특정 사용자의 정보를 업데이트합니다.
     *
     * <p>{@link UpdateUserinfoRepository}를 이용하여 사용자 정보를 수정합니다.</p>
     *
     * @param clientId 정보를 업데이트할 사용자 클라이언트 ID
     * @param updateOption 업데이트할 항목의 옵션 (예: 1 - 이름, 2 - 전화번호, 3 - 이메일 등)
     * @param newValue 업데이트할 새로운 값
     * @return 업데이트가 성공하면 {@code true}, 실패하면 {@code false}
     */
    @Override
    public boolean updateUserinfo(String clientId, int updateOption, String newValue) {
        return updateUserinfoRepository.updateUserinfo(clientId, updateOption, newValue);
    }

    /**
     * 특정 사용자의 최신 정보를 조회합니다.
     *
     * <p>{@link UpdateUserinfoRepository}를 이용하여 데이터베이스에서 최신 정보를 가져옵니다.</p>
     *
     * @param clientId 조회할 사용자 클라이언트 ID
     * @return 업데이트된 사용자 정보가 포함된 {@link UserDto} 객체, 사용자가 존재하지 않으면 {@code null}
     */
    @Override
    public UserDto getUpdateUserinfo(String clientId) {
        return updateUserinfoRepository.getUpdatedUserinfo(clientId);
    }
}
