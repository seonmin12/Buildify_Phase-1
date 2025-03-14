package domain.UserManagement.repository;

import dto.AdminDto;

import java.util.Optional;

public interface LoginRepository {

    /**
     * id를 넣고 일치하는 관리자 정보를 DB에서 가져오는 Repository
     * @param id
     * @return AdminDto
     */
    AdminDto login(String id);
}
