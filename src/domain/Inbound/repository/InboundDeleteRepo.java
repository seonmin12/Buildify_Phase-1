package domain.Inbound.repository;

import dto.InboundDto;

import java.util.List;

public interface InboundDeleteRepo {

    List<InboundDto> deleteSearch(String a);

    void Delete(String  inboundDto);
}
