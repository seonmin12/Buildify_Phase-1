package domain.UserManagement.controller;

import dto.AdminDto;

public interface LoginController {
    /**
     * 관리자 로그인 기능
     * @return adminDto
     */
    AdminDto login();

    /**
     * 로그인 되어있는 관리자 객체 호출
     * @return adminDto
     */
    AdminDto getAdminLoginStatus();
}
