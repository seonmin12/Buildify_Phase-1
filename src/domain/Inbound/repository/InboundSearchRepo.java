package domain.Inbound.repository;

import dto.ClientUpdateDto;
import dto.InboundDto;
import dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface InboundSearchRepo {


    /**
     * 입고요청진행 업체리스트 조회
     * @return
     */
    List<ClientUpdateDto> clientsearch();


    /**
     * 회원 입고요청 리스트 업체별 조회
     * @param a
     * @return
     */
    List<InboundDto> userSearch(String a);

    /**
     * 관리자 입고요청 리스트 업체별 조회
     * @param inbound_number
     * @return
     */
    List<InboundDto> SearchOne(String inbound_number);

    /**
     * 관리자 입고요청 리스트 전체조회
     * @return
     */
    Optional<List<InboundDto>> SearchAll();

}