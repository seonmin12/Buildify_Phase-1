package domain.DH_UserManagement.repository;

public interface UpdateUserinfoRepository {
    boolean updatePassword(String newpassword, String confirmpassword);

    boolean updateUserName(String newusername);

    boolean updateUserEmail(String newemail);

    boolean updateUserPhone(String newphone);
}
