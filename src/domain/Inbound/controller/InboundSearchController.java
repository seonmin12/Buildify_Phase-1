package domain.Inbound.controller;

import dto.InboundDto;
import dto.UserDto;

public interface InboundSearchController {

    /**
     * 회원 입고요청 전체조회
     * @param userDto
     */
    void userSearchAll(UserDto userDto);

    /**
     * 관리자 입고요청 전체조회
     */
    void SearchAll();

    /**
     * 관리자 업체별 조회
     */
    void SearchOne();
}