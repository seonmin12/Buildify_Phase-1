package domain.DH_UserManagement.service;

import dto.UserDto;

public interface SignUpService {
    boolean registerUser(UserDto userDto);
}
