package domain.DH_UserManagement.repository;

public interface FindPasswordRepository {
    boolean findPassword(String businessNumber, String email);
}
