package domain.Inbound.service;

import dto.InboundDto;

import java.util.List;

public interface InboundDeleteService {

    List<InboundDto> deletesearch(String a);

    void Delete(String inbound_number);
}
