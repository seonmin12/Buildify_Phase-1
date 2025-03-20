package domain.Inbound.repository;

import dto.InboundDto;

import java.util.List;

public interface InboundDeleteRepo {

    /**
     * 회원 입고삭제 가능 리스트 조회
     * @param a
     * @return
     */
    List<InboundDto> deleteSearch(String a);

    /**
     * 회원 입고요청 삭제
     * @param inboundDto
     */
    void Delete(String  inboundDto);
}
