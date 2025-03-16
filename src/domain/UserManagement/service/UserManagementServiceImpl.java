package domain.UserManagement.service;

import domain.UserManagement.repository.UserManagementRepository;
import dto.AdminDto;
import dto.UserDto;

import java.util.List;

public class UserManagementServiceImpl implements UserManagementService{
    private final UserManagementRepository userManagementRepository;

    public UserManagementServiceImpl(UserManagementRepository userManagementRepository) {
        this.userManagementRepository = userManagementRepository;
    }

    @Override
    public List<UserDto> listAllUsers() {
        return userManagementRepository.listAllUsers();
    }

    @Override
    public List<AdminDto> listAllLocalAdmin() {
        return userManagementRepository.listAllLocalAdmin();
    }

    @Override
    public List<UserDto> pendingApprovalUsers() {
        return userManagementRepository.pendingApprovalUsers();
    }

    @Override
    public void approveUser(String Client_id) {
        userManagementRepository.approveUser(Client_id);
    }

    @Override
    public UserDto searchUser(String Client_id) {
        return userManagementRepository.searchUser(Client_id);
    }

    @Override
    public void updateUser(String Client_id, Integer choice, String newValue) {
        userManagementRepository.updateUser(Client_id,choice,newValue);
    }

    @Override
    public void updateSelfAdmin(String Admin_id, Integer choice, String newValue) {
        userManagementRepository.updateSelfAdmin(Admin_id,choice,newValue);
    }
}
