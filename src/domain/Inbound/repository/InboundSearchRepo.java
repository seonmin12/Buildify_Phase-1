package domain.Inbound.repository;

import dto.ClientUpdateDto;
import dto.InboundDto;
import dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface InboundSearchRepo {



    List<ClientUpdateDto> clientsearch();

    List<InboundDto> userSearch(String a);

    List<InboundDto> SearchOne(String inbound_number);
    Optional<List<InboundDto>> SearchAll();

}