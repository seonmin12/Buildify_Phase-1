package domain.Inbound.repository;

import domain.Inbound.controller.InboundSearchController;
import dto.InboundDto;
import dto.InventoryDto;
import dto.ProductDto;
import dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface InboundInsertRepo {

    /**
     * 회원 입고요청 상품리스트 조회
     * @return
     */
    List<ProductDto> inboundinsertlist();

    /**
     * 회원 입고요청
     * @param inboundDto
     */
    void insert(InboundDto inboundDto);
}
