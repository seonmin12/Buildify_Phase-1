package domain.AccountManagement.User.controller;

/**
 * 상품 관련 기능을 제공하는 컨트롤러 인터페이스.
 *
 * <p>이 인터페이스는 상품 등록 요청 및 전체 상품 조회 기능을 정의합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public interface ProductController {

    /**
     * 상품 등록을 요청합니다.
     *
     * @return 상품 등록 요청이 성공하면 {@code true}, 실패하면 {@code false}
     */
    boolean requestProdcutRegist();

    /**
     * 모든 상품 정보를 조회합니다.
     *
     * @return 상품 조회가 성공하면 {@code true}, 실패하면 {@code false}
     */
    boolean getAllProduct();
}
