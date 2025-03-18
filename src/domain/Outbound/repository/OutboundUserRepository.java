package domain.Outbound.repository;

import dto.OutboundDto;

import java.util.List;

/**
 * (고객용) 출고관리 Repository 인터페이스입니다.
 */
public interface OutboundUserRepository {

    List<OutboundDto> outboundUserRead(String clientID);

    int deleteOutboundUser(String outboundNumber, String clientID);

    List<OutboundDto> getOutboundUserRequest(String clientID);
}
