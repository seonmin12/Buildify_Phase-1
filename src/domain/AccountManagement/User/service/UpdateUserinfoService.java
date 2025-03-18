package domain.AccountManagement.User.service;

import dto.UserDto;

public interface UpdateUserinfoService {
    boolean updateUserinfo(String clientId, int updateOption, String newValue);
}
