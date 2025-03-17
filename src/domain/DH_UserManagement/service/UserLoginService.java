package domain.DH_UserManagement.service;

import dto.UserDto;

public interface UserLoginService {
    UserDto login(String userid, String password);
}
