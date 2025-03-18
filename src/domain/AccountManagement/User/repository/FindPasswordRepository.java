package domain.AccountManagement.User.repository;

public interface FindPasswordRepository {
    boolean findPassword(String businessNumber, String email);
}
