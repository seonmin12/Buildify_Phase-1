package domain.Inbound.service;

import dto.InboundDto;

import java.util.List;

public interface InboundDeleteService {

    /**
     * 회원 입고요청 삭제가능 조회
     * @param a
     * @return
     */
    List<InboundDto> deletesearch(String a);

    /**
     * 회원 입고요청 삭제
     * @param inbound_number
     */
    void Delete(String inbound_number);
}
