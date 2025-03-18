package domain.AccountManagement.User.service;

import dto.UserDto;

public interface UserLoginService {
    UserDto login(String userid, String password);
}
