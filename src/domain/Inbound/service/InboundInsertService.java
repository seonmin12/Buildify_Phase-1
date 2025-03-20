package domain.Inbound.service;

import dto.InboundDto;
import dto.InventoryDto;
import dto.ProductDto;

import java.util.List;

public interface InboundInsertService {
    /**
     * 회원 입고요청 상품리스트 조회
     * @return
     */
    List<ProductDto> inboundinsertlist();

    /**
     * 회원 입고요청
     * @param inboundDto
     * @return
     */
    InboundDto insert(InboundDto inboundDto);
}
