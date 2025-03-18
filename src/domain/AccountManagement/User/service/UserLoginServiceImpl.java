package domain.AccountManagement.User.service;

import domain.AccountManagement.User.repository.UserLoginRepository;
import dto.UserDto;

public class UserLoginServiceImpl implements UserLoginService {
    private final UserLoginRepository userLoginRepository;

    public UserLoginServiceImpl(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    @Override
    public UserDto login(String userid, String password) {
        return userLoginRepository.login(userid, password);
    }
}
