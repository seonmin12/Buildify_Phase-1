package domain.Inbound.service;

import dto.InboundDto;

import java.util.List;

public interface InboundCheckService {

    List<InboundDto> allCheckRead();

    void allCheckUpdate();
    void allCheckReturn();
    List<InboundDto> clientCheckRead(String ci);
    void clientCheckUpdate(String ci);
    void clientCheckReturn(String ci);

    void numCheckUpdate(String ci);
    void numCheckReturn(String ci);





}
