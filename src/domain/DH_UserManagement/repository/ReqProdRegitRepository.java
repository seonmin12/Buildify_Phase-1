package domain.DH_UserManagement.repository;

import dto.ProductDto;

public interface ReqProdRegitRepository {
    boolean insertProduct(ProductDto productDto);
}
