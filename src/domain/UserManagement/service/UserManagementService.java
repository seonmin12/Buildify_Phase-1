package domain.UserManagement.service;

import dto.AdminDto;
import dto.UserDto;

import java.util.List;

public interface UserManagementService {
    List<UserDto> listAllUsers();
    List<AdminDto> listAllLocalAdmin();

    //승인대기회원조회
    List<UserDto> pendingApprovalUsers();
    //승인
    void approveUser(String Client_id);
    UserDto searchUser(String Client_id);
    void updateUser(String Client_id,Integer choice,String newValue);
    void updateSelfAdmin(String Admin_id,Integer choice,String newValue);

    int getUseWareSize();
}
