package domain.DH_UserManagement.repository;

public interface CheckUserApprovalRepository {
    boolean isUserApproved(String userId);
}
