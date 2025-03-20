package domain.Inbound.controller;

import dto.InboundDto;
import dto.ProductDto;
import dto.UserDto;

public interface InboundInsertController {


    /**
     * 회원 입고요청
     * @param inboundDto
     * @return
     */
    InboundDto insert(InboundDto inboundDto);


    /**
     * 회원 입고요청시작
     * @param userDto
     * @return
     */
    boolean insertrun(UserDto userDto);
}
