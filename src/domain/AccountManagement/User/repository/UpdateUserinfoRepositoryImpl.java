package domain.AccountManagement.User.repository;

public class UpdateUserinfoRepositoryImpl implements UpdateUserinfoRepository {
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
