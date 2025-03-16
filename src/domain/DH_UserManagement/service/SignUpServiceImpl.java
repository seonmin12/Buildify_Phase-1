package domain.DH_UserManagement.service;

import domain.DH_UserManagement.repository.SignUpRepository;
import domain.UserManagement.repository.LoginRepository;
import dto.UserDto;

public class SignUpServiceImpl implements SignUpService {

    private final SignUpRepository signUpRepository;

    public SignUpServiceImpl(SignUpRepository signUpRepository) {
        this.signUpRepository = signUpRepository;
    }

    @Override
    public boolean registerUser(UserDto userDto) {
        return signUpRepository.InsertUser(userDto);
    }

    @Override
    public boolean duplicateCheckUserID(String userid) {
        return signUpRepository.duplicateCheckUserID(userid);
    }
}
