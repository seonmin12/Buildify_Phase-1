package domain.UserManagement.controller;

import dto.AdminDto;

public interface UserManagementController {


    /**
     * (관리자메뉴)승인 대기 회원 조회 메소드입니다.
     * @param adminDto
     */
    void pendingApprovalUsers(AdminDto adminDto);

    /**
     * (관리자메뉴)승인 대기 회원 승인 메소드입니다.
     * @param adminDto
     */
    void approveUser(AdminDto adminDto);

    /**
     * (관리자메뉴)전체 회원 조회 메소드입니다.
     * @param adminDto
     */
    void listAllUsers(AdminDto adminDto);

    /**
     * (관리자메뉴)회원 1명 검색 메소드입니다.
     * @param adminDto
     */
    void searchUser(AdminDto adminDto);

    /**
     * (관리자메뉴)회원정보수정 메소드입니다.
     * @param adminDto
     */
    void updateUser(AdminDto adminDto);

    /**
     * (관리자메뉴)관리자 본인 정보 수정 메소드입니다.
     * @param adminDto
     */
    void updateSelfAdmin(AdminDto adminDto);
    /**
     * (총관리자 전용 메뉴)창고관리자 정보수정 메소드입니다.
     * @param adminDto
     */
    void updateAdmin(AdminDto adminDto);

    /**
     * (총관리자 전용 메뉴)창고관리자 조회 메소드입니다.
     * @param adminDto
     */
    void listAllLocalAdmin(AdminDto adminDto);

    /**
     * (관리자 메뉴) 내 정보 조회 메소드입니다.
     * @param admindto
     */
    void searchMyInfo(AdminDto admindto);

}
