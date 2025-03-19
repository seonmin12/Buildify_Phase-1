package domain.Inbound.repository;

import domain.Inbound.controller.InboundSearchController;
import dto.InboundDto;
import dto.InventoryDto;
import dto.ProductDto;

import java.sql.SQLException;
import java.util.List;

public interface InboundInsertRepo {

    List<ProductDto> inboundinsertlist();
    void insert(InboundDto inboundDto);
}
