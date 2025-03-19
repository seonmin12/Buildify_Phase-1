package domain.Inbound.controller;

import dto.InboundDto;
import dto.ProductDto;
import dto.UserDto;

public interface InboundInsertController {

    

    InboundDto insert(InboundDto inboundDto);



    boolean insertrun(UserDto userDto);
}
