package domain.DH_UserManagement.service;

import dto.ProductDto;

import java.util.List;

public interface ProductService {
    boolean registerProduct(ProductDto productDto);

    List<ProductDto> getAllProduct();
}
