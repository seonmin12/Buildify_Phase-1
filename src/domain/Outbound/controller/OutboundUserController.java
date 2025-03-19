package domain.Outbound.controller;

import dto.OutboundDto;

import java.util.List;

/**
 * (고객용) 출고관리 컨트롤러 인터페이스입니다.
 */
public interface OutboundUserController {
    List<OutboundDto> outboundUserRead(String clientId);

    void outboundUserDelete(String clientId);

    boolean requestOutbound(String clientId);

    boolean insertOutboundUser(OutboundDto outboundDto);
}
