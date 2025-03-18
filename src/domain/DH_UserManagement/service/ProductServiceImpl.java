package domain.DH_UserManagement.service;
import domain.DH_UserManagement.repository.ProductRepository;
import dto.ProductDto;

import java.util.List;


public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean registerProduct(ProductDto productDto) {
        return productRepository.insertProduct(productDto);
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return productRepository.getAllProduct();
    }
}
