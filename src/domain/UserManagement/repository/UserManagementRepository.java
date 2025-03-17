package domain.UserManagement.repository;

import dto.AdminDto;
import dto.UserDto;

import java.util.List;

public interface UserManagementRepository {
    /**
     * DB에서 전체 고객 리스트 반환 하는 메소드입니다.
     * @return List<UserDto>
     */
    List<UserDto> listAllUsers();

    /**
     * DB에서 전체 창고 관리자 리스트 반환 하는 메소드입니다.
     * @return List<AdminDto>
     */
    List<AdminDto> listAllLocalAdmin();

    /**
     * DB에서 승인 대기중인 고객 리스트 반환하는 메소드입니다.
     * @return List<UserDto>
     */
    List<UserDto> pendingApprovalUsers();

    /**
     * 고객 창고 사용 승인하는 메소드입니다.
     * @param Client_id
     */
    void approveUser(String Client_id);

    /**
     * 고객 1명 검색하여 객체로 반환하는 메소드입니다.
     * @param Client_id
     * @return UserDto
     */
    UserDto searchUser(String Client_id);

    /**
     * 업데이트할 정보를 선택하여 고객정보를 업데이트하는 메소드입니다.
     * @param Client_id
     * @param choice
     * @param newValue
     */
    void updateUser(String Client_id,Integer choice,String newValue);

    /**
     * 관리자 본인 정보 업데이트하는 메소드입니다.
     * @param Admin_id
     * @param choice
     * @param newValue
     */
    void updateSelfAdmin(String Admin_id,Integer choice,String newValue);

    /**
     * 현재 임대중인 창고 사용량을 보여주는 메소드입니다.
     * @return int UseWareSize
     */
    int getUseWareSize();

    /**
     * 관리자 number로 관리자 1명 검색하는 메소드입니다.
     * @param Admin_id
     * @return AdminDto
     */
    AdminDto searchAdmin(String Admin_id);
}
