package domain.DH_UserManagement.repository;

import dto.UserDto;

public interface UserLoginRepository {
    UserDto login(String userid, String password);
}
