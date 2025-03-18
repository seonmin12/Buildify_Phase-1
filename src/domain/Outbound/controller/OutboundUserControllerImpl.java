package domain.Outbound.controller;

import common.ValidCheck;
import domain.Outbound.service.OutboundUserService;
import dto.InventoryDto;
import dto.OutboundDto;
import dto.ReqOutboundDto;

import java.util.List;

/**
 * (고객용) 출고관리 컨트롤러 구현체입니다.
 */
public class OutboundUserControllerImpl implements OutboundUserController{
    private OutboundUserService outboundUserService;
    private ValidCheck validCheck;

    public OutboundUserControllerImpl(OutboundUserService outboundUserService, ValidCheck validCheck) {
        this.outboundUserService = outboundUserService;
        this.validCheck = validCheck;
    }


    @Override
    public List<OutboundDto> outboundUserRead() {

        System.out.println("[회원 출고 정보 조회]");
        System.out.print("고객 ID(client_id)를 입력하세요: ");
        String clientId = validCheck.inputAnyString();

        List<OutboundDto> outboundDtoList = outboundUserService.outboundUserRead(clientId);


        if (outboundDtoList == null || outboundDtoList.isEmpty()) {
            System.out.println("해당 입점사의 출고 정보가 없습니다.");
            return null;
        }

        for (OutboundDto dto : outboundDtoList) {
            System.out.printf("출고번호:%-8s | 상품ID:%-6s | 입점사ID:%-6s | 출고수량:%-8d | 출고상태:%4d | 출고요청일:%s | 창고ID:%s\n",
                    dto.getOutbound_id(), dto.getProd_id(), dto.getClient_id(),dto.getQuantity(),
                    dto.getOutbound_status(),dto.getReq_outbound_day(),dto.getWare_id());
        }
        return outboundDtoList;
    }

    @Override
    public void outboundUserDelete() {
        System.out.println("[회원 출고 취소 신청]");
        System.out.print("고객 ID(client_ID)를 입력하세요: ");
        String clientId = validCheck.inputAnyString();

        List<OutboundDto>pendinglist = outboundUserService.getOutboundRequests(clientId);
        if(pendinglist == null || pendinglist.isEmpty()) {
            System.out.println("미승인 출고 요청이 없습니다");
            return;
        }

        System.out.println("\n[미승인 출고 요청 목록]");
        for (OutboundDto dto : pendinglist) {
            System.out.printf("출고번호:%-8s | 상품ID:%-6s | 입점사ID:%-6s | 출고수량:%-8d | 출고상태:%4d | 출고요청일:%s | 창고ID:%s\n",
                    dto.getOutbound_id(), dto.getProd_id(), dto.getClient_id(),dto.getQuantity(),
                    dto.getOutbound_status(),dto.getReq_outbound_day(),dto.getWare_id());
        }

        System.out.print("\n취소할 출고번호를 입력하세요: ");
        String outbound_number = validCheck.inputAnyString();

        boolean success = outboundUserService.outboundUserDelete(outbound_number,clientId);

        if (success) {
            System.out.println("출고 요청이 성공적으로 취소되었습니다.");
        }else{
            System.out.println("출고 요청 취소에 실패했습니다. 출고번호를 확인해주세요.");
        }

    }

    @Override
    public boolean requestOutbound(String clientId) {
        List<ReqOutboundDto> outboundList = outboundUserService.requestOutbound(clientId);

        if(outboundList == null || outboundList.isEmpty()) {
            System.out.println("출고 요청 가능한 목록이 없습니다,");
            return false;
        }

        int index = 1;
        for (ReqOutboundDto dto : outboundList) {
            System.out.println(index++ + ". " + dto);
        }

        System.out.print("\n출고 요청할 상품 번호를 선택하세요 (취소하려면 0 입력): ");

        int select = validCheck.inputNumRegex();

        if(select == 0){
            return true;
        } else {
            ReqOutboundDto reqOutboundDto = outboundList.get(select - 1);

            int amount;
            do {
                System.out.println("수량을 입력하세요: ");
                amount = Integer.parseInt(validCheck.inputAnyString());

                if (amount > reqOutboundDto.getQuantity()) {
                    System.out.println("❌ 재고 수량보다 많게 입력할 수 없습니다. 다시 입력해주세요.");
                }
            } while (amount > reqOutboundDto.getQuantity());  // 🔥 조건을 만족할 때까지 반복

            System.out.println("\n✅ 출고 요청이 완료되었습니다.");
            System.out.println("출고 요청 상품: " + reqOutboundDto);
            System.out.println("출고 수량: " + amount);

            // 출고 요청하는 repo 기능 동작 필요
        }

        return true;
    }


}
