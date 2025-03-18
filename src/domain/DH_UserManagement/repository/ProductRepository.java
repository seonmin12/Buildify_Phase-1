package domain.DH_UserManagement.repository;

import dto.ProductDto;

import java.util.List;

public interface ProductRepository {
    boolean insertProduct(ProductDto productDto);

    List<ProductDto> getAllProduct();
}
