package domain.Outbound.service;

import dto.OutboundDto;

import java.util.List;

/**
 * (고객용) 출고관리 Service 인터페이스입니다.
 */
public interface OutboundUserService {
    List<OutboundDto> outboundUserRead(String clientID);
}
