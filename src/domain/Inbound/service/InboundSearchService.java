package domain.Inbound.service;

import dto.InboundDto;

import java.util.List;

public interface InboundSearchService {

    List<InboundDto> userSearchAll(String a);

    List<InboundDto> SearchOne(String inbound_number);

    List<InboundDto> SearchAll();
}