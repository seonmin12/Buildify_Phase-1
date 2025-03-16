package domain.DH_UserManagement.controller;

public interface UpdateUserinfoController {

    boolean updatePassword(String newpassword, String confirmpassword);

    boolean updateUserName(String newusername);

    boolean updateUserEmail(String newemail);

    boolean updateUserPhone(String newphone);
}
