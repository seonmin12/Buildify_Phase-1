package domain.AccountManagement.User.controller;

public interface UpdateUserinfoController {

    boolean updatePassword(String newpassword, String confirmpassword);

    boolean updateUserName(String newusername);

    boolean updateUserEmail(String newemail);

    boolean updateUserPhone(String newphone);
}
