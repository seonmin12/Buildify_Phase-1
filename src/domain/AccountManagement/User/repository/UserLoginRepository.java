package domain.AccountManagement.User.repository;

import dto.UserDto;

public interface UserLoginRepository {
    UserDto login(String userid, String password);
}
