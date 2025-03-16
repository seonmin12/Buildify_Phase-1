package domain.DH_UserManagement.repository;

public interface UserLoginRepository {
    boolean login(String userid, String password);
}
