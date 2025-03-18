package domain.AccountManagement.User.service;

import dto.UserDto;

public interface SignUpService {
    boolean registerUser(UserDto userDto);

    boolean duplicateCheckUserID(String userid);
}
