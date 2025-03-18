package domain.AccountManagement.User.repository;

import dto.UserDto;

public interface UpdateUserinfoRepository {
    boolean updateUserinfo(String clientId, int updateOption, String newValue);
}
