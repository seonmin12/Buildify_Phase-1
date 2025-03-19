package domain.Inbound.service;

import dto.InboundDto;
import dto.InventoryDto;
import dto.ProductDto;

import java.util.List;

public interface InboundInsertService {

    List<ProductDto> inboundinsertlist();

    InboundDto insert(InboundDto inboundDto);
}
