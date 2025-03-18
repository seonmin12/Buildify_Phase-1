package domain.AccountManagement.User.service;

import domain.AccountManagement.User.repository.SignUpRepository;
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
