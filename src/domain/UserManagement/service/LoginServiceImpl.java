package domain.UserManagement.service;

import domain.UserManagement.repository.LoginRepository;
import dto.AdminDto;

public class LoginServiceImpl implements LoginService{

   private final LoginRepository loginRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public AdminDto adminlogin(String id) {
        return loginRepository.login(id);
    }
}
