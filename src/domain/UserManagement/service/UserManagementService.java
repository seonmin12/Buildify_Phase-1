package domain.UserManagement.service;

import dto.AdminDto;
import dto.UserDto;

import java.util.List;

/**
 * 사용자 및 관리자 계정을 관리하는 서비스 인터페이스.
 * 사용자 목록 조회, 승인, 업데이트 등의 기능을 제공한다.
 */
public interface UserManagementService {

    /**
     * 모든 사용자 목록을 조회한다.
     * @return 사용자 목록 (List<UserDto>)
     */
    List<UserDto> listAllUsers();

    /**
     * 모든 로컬 관리자 목록을 조회한다.
     * @return 로컬 관리자 목록 (List<AdminDto>)
     */
    List<AdminDto> listAllLocalAdmin();

    /**
     * 승인 대기 중인 사용자 목록을 조회한다.
     * @return 승인 대기 중인 사용자 목록 (List<UserDto>)
     */
    List<UserDto> pendingApprovalUsers();

    /**
     * 특정 사용자의 승인을 처리한다.
     * @param clientId 승인할 사용자의 ID
     */
    void approveUser(String clientId);

    /**
     * 특정 사용자를 검색한다.
     * @param clientId 검색할 사용자의 ID
     * @return 검색된 사용자 정보 (UserDto)
     */
    UserDto searchUser(String clientId);

    /**
     * 특정 사용자의 정보를 업데이트한다.
     * @param clientId  업데이트할 사용자의 ID
     * @param choice    변경할 항목의 선택지 (예: 1: 이메일, 2: 전화번호 등)
     * @param newValue  변경할 새로운 값
     */
    void updateUser(String clientId, Integer choice, String newValue);

    /**
     * 특정 관리자의 정보를 업데이트한다.
     * @param adminId   업데이트할 관리자의 ID
     * @param choice    변경할 항목의 선택지 (예: 1: 이름, 2: 연락처 등)
     * @param newValue  변경할 새로운 값
     */
    void updateSelfAdmin(String adminId, Integer choice, String newValue);

    /**
     * 현재 사용 가능한 웨어하우스(저장소) 크기를 반환한다.
     * @return 사용 가능한 웨어하우스 크기
     */
    int getUseWareSize();

    /**
     * 특정 관리자를 검색한다.
     * @param adminId 검색할 관리자의 ID
     * @return 검색된 관리자 정보 (AdminDto)
     */
    AdminDto searchAdmin(String adminId);
}