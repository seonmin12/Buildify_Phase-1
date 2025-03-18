package domain.AccountManagement.Admin.service;

import domain.AccountManagement.Admin.repository.UserManagementRepository;
import dto.AdminDto;
import dto.UserDto;

import java.util.List;

public class UserManagementServiceImpl implements UserManagementService{
    private final UserManagementRepository userManagementRepository;

    public UserManagementServiceImpl(UserManagementRepository userManagementRepository) {
        this.userManagementRepository = userManagementRepository;
    }

    /**
     * 모든 사용자 목록을 조회한다.
     * @return 사용자 목록 (List<UserDto>)
     */
    @Override
    public List<UserDto> listAllUsers() {
        return userManagementRepository.listAllUsers();
    }

    /**
     * 모든 로컬 관리자 목록을 조회한다.
     * @return 로컬 관리자 목록 (List<AdminDto>)
     */
    @Override
    public List<AdminDto> listAllLocalAdmin() {
        return userManagementRepository.listAllLocalAdmin();
    }
    /**
     * 승인 대기 중인 사용자 목록을 조회한다.
     * @return 승인 대기 중인 사용자 목록 (List<UserDto>)
     */
    @Override
    public List<UserDto> pendingApprovalUsers() {
        return userManagementRepository.pendingApprovalUsers();
    }
    /**
     * 특정 사용자의 승인을 처리한다.
     * @param clientId 승인할 사용자의 ID
     */
    @Override
    public void approveUser(String Client_id) {
        userManagementRepository.approveUser(Client_id);
    }
    /**
     * 특정 사용자를 검색한다.
     * @param clientId 검색할 사용자의 ID
     * @return 검색된 사용자 정보 (UserDto)
     */
    @Override
    public UserDto searchUser(String Client_id) {
        return userManagementRepository.searchUser(Client_id);
    }
    /**
     * 특정 사용자의 정보를 업데이트한다.
     * @param clientId  업데이트할 사용자의 ID
     * @param choice    변경할 항목의 선택지 (예: 1: 이메일, 2: 전화번호 등)
     * @param newValue  변경할 새로운 값
     */
    @Override
    public void updateUser(String Client_id, Integer choice, String newValue) {
        switch (choice){
            case 1 -> userManagementRepository.updateUserPhone(Client_id,newValue);
            case 2 -> userManagementRepository.updateUserEmail(Client_id,newValue);
            case 3 -> userManagementRepository.updateUserAddress(Client_id,newValue);
        }
    }
    /**
     * 특정 관리자의 정보를 업데이트한다.
     * @param adminId   업데이트할 관리자의 ID
     * @param choice    변경할 항목의 선택지 (예: 1: 이름, 2: 연락처 등)
     * @param newValue  변경할 새로운 값
     */
    @Override
    public void updateSelfAdmin(String Admin_id, Integer choice, String newValue) {
        switch (choice){
            case 1 -> userManagementRepository.updateSelfAdminPhone(Admin_id,newValue);
            case 2 -> userManagementRepository.updateSelfAdminEmail(Admin_id,newValue);
            case 3 -> userManagementRepository.updateSelfAdminAddress(Admin_id,newValue);
        }
    }
    /**
     * 현재 사용 가능한 웨어하우스(저장소) 크기를 반환한다.
     * @return 사용 가능한 웨어하우스 크기
     */
    @Override
    public int getUseWareSize() {
        return userManagementRepository.getUseWareSize();
    }
    /**
     * 특정 관리자를 검색한다.
     * @param adminId 검색할 관리자의 ID
     * @return 검색된 관리자 정보 (AdminDto)
     */
    @Override
    public AdminDto searchAdmin(String admin_id) {
        return userManagementRepository.searchAdmin(admin_id);
    }

}
