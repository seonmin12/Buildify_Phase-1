package controller;

import dto.AdminDto;

public interface WarehouseController {

    void start();
    void adminStart();
    void userStart();
    void signUp();

    void adminUserManagement(AdminDto adminDto);
    void adminInboundStart(AdminDto adminDto);
    void adminOutboundStart(AdminDto adminDto);
    void adminInventoryStart(AdminDto adminDto);
}
