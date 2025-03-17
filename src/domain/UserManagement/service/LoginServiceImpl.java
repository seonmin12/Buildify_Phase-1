package domain.UserManagement.service;

import domain.UserManagement.repository.LoginRepository;
import dto.AdminDto;

public class LoginServiceImpl implements LoginService{

   private final LoginRepository loginRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    /**
     * 컨트롤러에서 입력받은 아이디를 Repository에 전달하는 메소드
     * @param id
     * @return LoginRepository.login()
     */
    @Override
    public AdminDto adminlogin(String id) {
        return loginRepository.login(id);
    }
}
