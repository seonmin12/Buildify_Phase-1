package domain.Outbound.controller;

import dto.OutboundDto;

import java.util.List;

/**
 * (고객용) 출고관리 컨트롤러 인터페이스입니다.
 */
public interface OutboundUserController {
    List<OutboundDto> outboundUserRead();

    void outboundUserDelete();

    boolean requestOutbound(String clientId);
}
