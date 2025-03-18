package domain.Outbound.controller;

import common.ValidCheck;
import domain.Outbound.service.OutboundAdminService;
import dto.OutboundDto;

import java.util.List;

/**
 * (관리자용) 출고관리 컨트롤러 구현체입니다.
 */
public class OutboundAdminControllerImpl implements OutboundAdminController {
    private final ValidCheck validCheck;
    private final OutboundAdminService outboundAdminService;

    public OutboundAdminControllerImpl(ValidCheck validCheck, OutboundAdminService outboundAdminService) {
        this.validCheck = validCheck;
        this.outboundAdminService = outboundAdminService;
    }

    /**
     * (관리자메뉴) 출고 리스트 전체 조회
     */
    @Override
    public void searchOutboundList() {
        List<OutboundDto> outboundDtoList = outboundAdminService.searchOutboundList();
        if (outboundDtoList.isEmpty()){
            System.out.println("출고리스트가 없습니다.");
            return;
        }
        System.out.println("출고번호 상품번호 고객번호 수량 출고상태 촐고요청일 창고ID");
        for (OutboundDto list : outboundDtoList){
            System.out.printf("%s %s %s %d %d %s %s",
                    list.getOutbound_number(),list.getProd_id(),
                    list.getClient_id(),list.getQuantity(),
                    list.getOutbound_status(),list.getReq_outbound_day(),
                    list.getWare_id());
        }
    }

    /**
     * (관리자메뉴) 고객별 출고 리스트 전체 조회
     */
    @Override
    public void searchOutboundByUser() {
        String client_id;
        System.out.println("조회할 고객의 Client ID 를 입력하세요.");
        System.out.printf("입력 : ");
        client_id = validCheck.inputAnyString();
        List<OutboundDto> outboundDtoList = outboundAdminService.searchOutboundByUser(client_id);
        if (outboundDtoList.isEmpty()){
            System.out.println("해당 고객의 출고리스트가 없습니다.");
            return;
        }
        System.out.println("출고번호 상품번호 고객번호 수량 출고상태 촐고요청일 창고ID");
        for (OutboundDto list : outboundDtoList){
            System.out.printf("%s %s %s %d %d %s %s",
                    list.getOutbound_number(),list.getProd_id(),
                    list.getClient_id(),list.getQuantity(),
                    list.getOutbound_status(),list.getReq_outbound_day(),
                    list.getWare_id());
        }
    }

    /**
     * (관리자메뉴) 출고 전체 승인
     */
    @Override
    public void approveAllList() {
        searchOutboundList();
        outboundAdminService.approveAllList();
    }

    /**
     * (관리자메뉴) 클라이언트 ID 기준 해당 고객 출고 전체 승인
     */
    @Override
    public void approveById() {
        String Client_id;
        System.out.println("승인할 Client ID 를 입력하세요.");
        System.out.printf("입력 : ");
        Client_id = validCheck.inputAnyString();
        outboundAdminService.approveOneId(Client_id);
    }

    /**
     * (관리자메뉴) 출고Number 기준 해당 출고건 승인
     */
    @Override
    public void approveOneNumber() {
        String outbound_number;
        System.out.println("승인할 출고 번호를 입력하세요.");
        System.out.printf("입력 : ");
        outbound_number = validCheck.inputAnyString();
        outboundAdminService.approveOneNumber(outbound_number);
    }

    /**
     * (관리자메뉴) 출고 Number 기준 해당 출고 반려
     */
    @Override
    public void returnOneNumber() {
        String outbound_number;
        System.out.println("반려할 출고 번호를 입력하세요.");
        System.out.printf("입력 : ");
        outbound_number = validCheck.inputAnyString();
        outboundAdminService.returnOneNumber(outbound_number);
    }
}
