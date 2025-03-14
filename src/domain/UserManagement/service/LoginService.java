package domain.UserManagement.service;

import dto.AdminDto;

public interface LoginService {

    /**
     * 컨트롤러에서 입력받은 아이디를 Repository에 전달하는 메소드
     * @param id
     * @return LoginRepository.login()
     */
    AdminDto adminlogin(String id);
}
