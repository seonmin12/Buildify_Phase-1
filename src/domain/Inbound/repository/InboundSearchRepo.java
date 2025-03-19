package domain.Inbound.repository;

import dto.InboundDto;

import java.util.List;
import java.util.Optional;

public interface InboundSearchRepo {

    List<InboundDto> clientsearch();

    List<InboundDto> userSearch(String a);

    List<InboundDto> SearchOne(String inbound_number);
    Optional<List<InboundDto>> SearchAll();

}