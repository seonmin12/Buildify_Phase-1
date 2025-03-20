package domain.Inbound.service;

import dto.ClientUpdateDto;
import dto.InboundDto;
import dto.UserDto;

import java.util.List;

public interface InboundSearchService {


    /**
     * 입고요청진행 업체리스트 조회
     * @return
     */
    List<ClientUpdateDto> clientupdatesearch();

    /**
     * 회원 입고요청 리스트 업체별 조회
     * @param a
     * @return
     */
    List<InboundDto> userSearchAll(String a);

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
    List<InboundDto> SearchAll();
}