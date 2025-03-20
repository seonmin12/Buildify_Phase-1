package domain.AccountManagement.User.service;

import dto.ProductDto;
import java.util.List;

/**
 * 상품 관련 비즈니스 로직을 제공하는 서비스 인터페이스.
 *
 * <p>이 인터페이스는 상품 등록 및 전체 상품 조회 기능을 정의합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public interface ProductService {

    /**
     * 새로운 상품을 등록합니다.
     *
     * @param productDto 등록할 상품 정보를 포함한 {@link ProductDto} 객체
     * @return 상품 등록이 성공하면 {@code true}, 실패하면 {@code false}
     */
    boolean registerProduct(ProductDto productDto);

    /**
     * 모든 상품 목록을 조회합니다.
     *
     * @return {@link ProductDto} 객체 리스트, 조회된 상품이 없을 경우 빈 리스트 반환
     */
    List<ProductDto> getAllProduct();
}
