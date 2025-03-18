package domain.AccountManagement.User.service;

public interface FindPasswordService {
    boolean findPassword(String businessNumber, String email);
}
