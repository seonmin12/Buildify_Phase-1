package domain.Outbound.repository;

import dto.OutboundDto;

import java.util.List;

/**
 * (관리자용) 출고관리 Repository 인터페이스입니다.
 */
public interface OutboundAdminRepository {
    /**
     * (관리자메뉴) 출고 리스트 전체 조회
     * @return List<OutboundDto> 출고 리스트 전체 조회
     */
    List<OutboundDto> searchOutboundList();
}
