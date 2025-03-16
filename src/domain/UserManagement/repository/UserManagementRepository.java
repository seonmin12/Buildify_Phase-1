package domain.UserManagement.repository;

import dto.AdminDto;
import dto.UserDto;

import java.util.List;

public interface UserManagementRepository {
    List<UserDto> listAllUsers();
    List<AdminDto> listAllLocalAdmin();

    List<UserDto> pendingApprovalUsers();
    void approveUser(String Client_id);
    UserDto searchUser(String Client_id);
    void updateUser(String Client_id,Integer choice,String newValue);

    void updateSelfAdmin(String Admin_id,Integer choice,String newValue);
}
