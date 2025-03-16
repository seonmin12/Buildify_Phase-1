package domain.DH_UserManagement.controller;

import dto.UserDto;

public interface FindPasswordController {
    boolean findPassword(String businessNumber, String email);
}
