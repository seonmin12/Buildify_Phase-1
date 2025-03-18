package domain.Inbound.service;

import dto.InboundDto;

import java.util.List;

public interface InboundSearchService {

    List<InboundDto> userSearchAll();

    List<InboundDto> SearchOne(String inbound_number);

    List<InboundDto> SearchAll();
}