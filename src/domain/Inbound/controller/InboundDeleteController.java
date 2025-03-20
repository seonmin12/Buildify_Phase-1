package domain.Inbound.controller;

import dto.UserDto;

public interface InboundDeleteController {

    /**
     * 회원 입고요청 삭제(대기상태만 삭제가능)
     */
    void delete(UserDto userDto);
}
