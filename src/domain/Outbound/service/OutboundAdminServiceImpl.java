package domain.Outbound.service;

import domain.Outbound.repository.OutboundAdminRepository;
import dto.OutboundDto;

import java.util.Collections;
import java.util.List;

/**
 * (관리자용) 출고관리 Service 구현체입니다.
 */
public class OutboundAdminServiceImpl implements OutboundAdminService {
    private final OutboundAdminRepository outboundAdminRepository;

    public OutboundAdminServiceImpl(OutboundAdminRepository outboundAdminRepository) {
        this.outboundAdminRepository = outboundAdminRepository;
    }

    /**
     * (관리자메뉴) 출고 리스트 전체 조회
     * @return List<OutboundDto> 출고 리스트 전체 조회
     */
    @Override
    public List<OutboundDto> searchOutboundList() {
        return outboundAdminRepository.searchOutboundList();
    }

    /**
     * (관리자메뉴) 출고 리스트 고객별 조회
     * @return List<OutboundDto> 고객별 출고 리스트 전체 조회
     */
    @Override
    public List<OutboundDto> searchOutboundByUser(String Client_id) {
        String validcheck = clientIdValidCheck(Client_id);
        if (validcheck.equals("FALSE")){
            System.out.println("존재하지 않은 고객입니다.");
            return Collections.emptyList();
        }
        return outboundAdminRepository.searchOutboundByUser(Client_id);
    }

    /**
     * (관리자메뉴) 출고 전체 승인
     */
    @Override
    public void approveAllList() {
        outboundAdminRepository.approveAllList();
    }

    /**
     * (관리자메뉴) 클라이언트 ID 기준 해당 고객 출고 전체 승인
     */
    @Override
    public void approveOneId(String Client_id) {
        String validcheck = clientIdValidCheck(Client_id);
        if (validcheck.equals("FALSE")){
            System.out.println("존재하지 않은 고객입니다.");
            return;
        }
        outboundAdminRepository.approveOneId(Client_id);
    }

    /**
     * (관리자메뉴) 출고Number 기준 해당 출고건 승인
     */
    @Override
    public void approveOneNumber(String outbound_number) {
        String validcheck = outboundNumberValidCheck(outbound_number);
        if (validcheck.equals("FALSE")){
            System.out.println("존재하지 않은 출고번호입니다.");
            return;
        }
        outboundAdminRepository.approveOneNumber(outbound_number);
    }

    /**
     * (관리자메뉴) 출고 Number 기준 해당 출고 반려
     */
    @Override
    public void returnOneNumber(String outbound_number) {
        String validcheck = outboundNumberValidCheck(outbound_number);
        if (validcheck.equals("FALSE")){
            System.out.println("존재하지 않은 출고번호입니다.");
            return;
        }
        outboundAdminRepository.returnOneNumber(outbound_number);
    }

    /**
     * Cleint ID가 DB에 있는지 검증하는 메소드
     * @param Client_id
     * @return "TRUE" or "FALSE"
     */
    @Override
    public String clientIdValidCheck(String Client_id) {
        String result = outboundAdminRepository.clientIdValidCheck(Client_id);
        return (result != null) ? result : "FALSE";
    }

    /**
     * 출고번호가 DB에 있는지 검증하는 메소드
     * @param number
     * @return "TRUE" or "FALSE"
     */
    @Override
    public String outboundNumberValidCheck(String number) {
        String result = outboundAdminRepository.outboundNumberValidCheck(number);
        return (result != null) ? result : "FALSE";
    }
}
