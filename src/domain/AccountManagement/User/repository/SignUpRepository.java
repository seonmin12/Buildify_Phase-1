package domain.AccountManagement.User.repository;

import dto.UserDto;

public interface SignUpRepository {
    boolean InsertUser(UserDto userDto);

    boolean duplicateCheckUserID(String userid);
}
