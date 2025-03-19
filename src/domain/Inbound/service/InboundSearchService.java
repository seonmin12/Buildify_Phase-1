package domain.Inbound.service;

import dto.ClientUpdateDto;
import dto.InboundDto;
import dto.UserDto;

import java.util.List;

public interface InboundSearchService {



    List<ClientUpdateDto> clientupdatesearch();

    List<InboundDto> userSearchAll(String a);

    List<InboundDto> SearchOne(String inbound_number);

    List<InboundDto> SearchAll();
}