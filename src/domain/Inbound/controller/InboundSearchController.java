package domain.Inbound.controller;

import dto.InboundDto;
import dto.UserDto;

public interface InboundSearchController {

    void userSearchAll(UserDto userDto);

    void SearchAll();
    void SearchOne();
}