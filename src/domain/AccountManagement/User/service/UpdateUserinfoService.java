package domain.AccountManagement.User.service;

public interface UpdateUserinfoService {
    boolean updatePassword(String newpassword, String confirmpassword);

    boolean updateUserName(String newusername);

    boolean updateUserEmail(String newemail);

    boolean updateUserPhone(String newphone);
}
