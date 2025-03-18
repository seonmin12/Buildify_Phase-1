package domain.Outbound.repository;

import dto.OutboundDto;
import dto.UserDto;

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

    /**
     * (관리자메뉴) 출고 리스트 고객별 조회
     * @return List<OutboundDto> 고객별 출고 리스트 전체 조회
     */
    List<OutboundDto> searchOutboundByUser(String Client_id);

    /**
     * (관리자메뉴) 출고 전체 승인
     */
    void approveAllList();

    /**
     * (관리자메뉴) 클라이언트 ID 기준 해당 고객 출고 전체 승인
     */
    void approveOneId(String Client_id);

    /**
     * (관리자메뉴) 출고Number 기준 해당 출고건 승인
     */
    void approveOneNumber(String outbound_number);

    /**
     * (관리자메뉴) 출고 Number 기준 해당 출고 반려
     */
    void returnOneNumber(String outbound_number);

    /**
     * Client id가 DB에 있는지 검증하는 메소드
     * @param Client_id
     * @return "TRUE" or "FALSE"
     */
    String clientIdValidCheck(String Client_id);

    /**
     * 출고번호가 DB에 있는지 검증하는 메소드
     * @param number
     * @return "TRUE" or "FALSE"
     */
    String outboundNumberValidCheck(String number);


}
