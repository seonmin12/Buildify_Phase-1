package domain.AccountManagement.User.service;

import domain.AccountManagement.User.repository.UpdateUserinfoRepository;
import dto.UserDto;

public class UpdateUserinfoServiceImpl implements UpdateUserinfoService {

    private final UpdateUserinfoRepository updateUserinfoRepository;

    public UpdateUserinfoServiceImpl(UpdateUserinfoRepository updateUserinfoRepository) {
        this.updateUserinfoRepository = updateUserinfoRepository;
    }

    @Override
    public boolean updateUserinfo(String clientId, int updateOption, String newValue) {
        return updateUserinfoRepository.updateUserinfo(clientId, updateOption, newValue);
    }
}
