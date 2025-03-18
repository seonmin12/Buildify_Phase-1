package domain.Outbound.controller;

import common.ValidCheck;
import domain.Outbound.service.OutboundAdminService;
import dto.OutboundDto;

import java.util.List;

import static common.Text.*;

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
            System.out.println(NOT_FOUND_OUNTBOUND.getText());
            return;
        }
        String status;
        System.out.printf("%-20s %-15s %-12s %4s %10s %10s %10s\n",
                "출고번호", "상품번호", "고객번호", "수량", "출고상태", "출고요청일", "창고ID");
        System.out.println(ROUND_BAR.getText());
        for (OutboundDto list : outboundDtoList) {
            if (list.getOutbound_status() == 0){
                status = "대기";
            }else if (list.getOutbound_status() == 1){
                status = "승인";
            }else status = "반려";
            System.out.printf("%-23s %-16s %-4s %14d %10s %15s %10s\n",
                    list.getOutbound_number(), list.getProd_id(),
                    list.getClient_id(), list.getQuantity(),
                    status, list.getReq_outbound_day(),
                    list.getWare_id());
        }
    }

    /**
     * (관리자메뉴) 고객별 출고 리스트 전체 조회
     */
    @Override
    public void searchOutboundByUser() {
        String client_id;
        System.out.println(SEARCH_USER_INPUT.getText());
        System.out.printf(INPUT_CHOICE.getText());
        client_id = validCheck.inputAnyString();
        List<OutboundDto> outboundDtoList = outboundAdminService.searchOutboundByUser(client_id);
        if (outboundDtoList.isEmpty()){
            System.out.println(NOT_FOUND_USER_OUTBOUND.getText());
            return;
        }
        String status;
        System.out.printf("%-20s %-15s %-12s %4s %10s %10s %10s\n",
                "출고번호", "상품번호", "고객번호", "수량", "출고상태", "출고요청일", "창고ID");
        System.out.println(ROUND_BAR.getText());
        for (OutboundDto list : outboundDtoList) {
            if (list.getOutbound_status() == 0){
                status = "대기";
            }else if (list.getOutbound_status() == 1){
                status = "승인";
            }else status = "반려";
            System.out.printf("%-23s %-16s %-4s %14d %10s %15s %10s\n",
                    list.getOutbound_number(), list.getProd_id(),
                    list.getClient_id(), list.getQuantity(),
                    status, list.getReq_outbound_day(),
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
        System.out.println(OUTBOUND_APPROVE_CLIENT.getText());
        System.out.printf(INPUT_CHOICE.getText());
        Client_id = validCheck.inputAnyString();
        outboundAdminService.approveOneId(Client_id);
    }

    /**
     * (관리자메뉴) 출고Number 기준 해당 출고건 승인
     */
    @Override
    public void approveOneNumber() {
        String outbound_number;
        System.out.println(OUTBOUND_APPROVE_NUMBER.getText());
        System.out.printf(INPUT_CHOICE.getText());
        outbound_number = validCheck.inputAnyString();
        outboundAdminService.approveOneNumber(outbound_number);
    }

    /**
     * (관리자메뉴) 출고 Number 기준 해당 출고 반려
     */
    @Override
    public void returnOneNumber() {
        String outbound_number;
        System.out.println(REJECT_OUTBOUND.getText());
        System.out.printf(INPUT_CHOICE.getText());
        outbound_number = validCheck.inputAnyString();
        outboundAdminService.returnOneNumber(outbound_number);
    }
}
