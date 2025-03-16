package domain.DH_UserManagement.controller;

import domain.DH_UserManagement.service.UserLoginService;

public class UpdateUserinfoControllerImpl implements UpdateUserinfoController {
    @Override
    public boolean updatePassword(String newpassword, String confirmpassword) {
        return false;
    }

    @Override
    public boolean updateUserName(String newusername) {
        return false;
    }

    @Override
    public boolean updateUserEmail(String newemail) {
        return false;
    }

    @Override
    public boolean updateUserPhone(String newphone) {
        return false;
    }
}
