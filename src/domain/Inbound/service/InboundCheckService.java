package domain.Inbound.service;

import dto.InboundDto;

import java.util.List;

public interface InboundCheckService {

    List<InboundDto> allCheckRead();

    void allCheckUpdate();
    void allCheckReturn();
    List<InboundDto> prodCheckRead(String prod);
    void prodCheckUpdate(String ci);
    void prodCheckReturn(String ci);
    List<InboundDto> clientCheckRead(String ci);
    void clientCheckUpdate(String ci);
    void clientCheckReturn(String ci);





}
