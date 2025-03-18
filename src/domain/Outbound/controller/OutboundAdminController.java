package domain.Outbound.controller;

import dto.OutboundDto;

import java.util.List;

/**
 * (관리자용) 출고관리 컨트롤러 인터페이스입니다.
 */
public interface OutboundAdminController {
    /**
     * (관리자메뉴) 출고 리스트 전체 조회
     */
    void searchOutboundList();

    /**
     * (관리자메뉴) 고객별 출고 리스트 전체 조회
     */
    void searchOutboundByUser();

    /**
     * (관리자메뉴) 출고 전체 승인
     */
    void approveAllList();

    /**
     * (관리자메뉴) 클라이언트 ID 기준 해당 고객 출고 전체 승인
     */
    void approveById();

    /**
     * (관리자메뉴) 출고Number 기준 해당 출고건 승인
     */
    void approveOneNumber();

    /**
     * (관리자메뉴) 출고 Number 기준 해당 출고 반려
     */
    void returnOneNumber();

}
