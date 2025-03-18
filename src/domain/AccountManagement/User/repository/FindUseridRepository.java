package domain.AccountManagement.User.repository;

public interface FindUseridRepository {
    String findUserIdByBusinessNumber(String businessNumber);
}
