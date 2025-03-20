package domain.AccountManagement.User.service;

import domain.AccountManagement.User.repository.ProductRepository;
import dto.ProductDto;
import java.util.List;

/**
 * {@link ProductService}의 구현체.
 *
 * <p>상품 등록 및 조회 기능을 수행하는 서비스 클래스입니다.</p>
 *
 * <p>이 클래스는 {@link ProductRepository}를 이용하여 데이터베이스와의 연동을 처리합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * {@code ProductServiceImpl} 생성자.
     *
     * @param productRepository 상품 데이터 관리를 위한 리포지토리 객체
     */
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 새로운 상품을 등록합니다.
     *
     * <p>{@link ProductRepository}를 이용하여 상품 정보를 데이터베이스에 저장합니다.</p>
     *
     * @param productDto 등록할 상품 정보를 포함한 {@link ProductDto} 객체
     * @return 상품 등록이 성공하면 {@code true}, 실패하면 {@code false}
     */
    @Override
    public boolean registerProduct(ProductDto productDto) {
        return productRepository.insertProduct(productDto);
    }

    /**
     * 모든 상품 목록을 조회합니다.
     *
     * <p>{@link ProductRepository}를 이용하여 데이터베이스에서 모든 상품 정보를 가져옵니다.</p>
     *
     * @return {@link ProductDto} 객체 리스트, 조회된 상품이 없을 경우 빈 리스트 반환
     */
    @Override
    public List<ProductDto> getAllProduct() {
        return productRepository.getAllProduct();
    }
}
