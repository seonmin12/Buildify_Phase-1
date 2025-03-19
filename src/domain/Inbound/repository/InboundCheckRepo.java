package domain.Inbound.repository;

import dto.InboundDto;

import java.util.List;

public interface InboundCheckRepo {

    List<InboundDto> allCheckRead();

    void allCheckUpdate();
    void allCheckReturn();
    List<InboundDto> prodCheckRead();
    void prodCheckUpdate();
    void prodCheckReturn();
    List<InboundDto> clientCheckRead(String ci);
    void clientCheckUpdate(String ci);
    void clientCheckReturn(String ci);

    void inbound_number_check_update(String ci);

    default void inbound_number_check_return(String ci) {

    }
}
