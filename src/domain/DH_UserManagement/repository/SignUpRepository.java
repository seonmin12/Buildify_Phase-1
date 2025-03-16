package domain.DH_UserManagement.repository;

import dto.UserDto;

public interface SignUpRepository {
    boolean InsertUser(UserDto userDto);

    boolean duplicateCheckUserID(String userid);
}
