package domain.AccountManagement.User.repository;

import config.DBConnection;
import dto.ProductDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link ProductRepository}의 구현체.
 *
 * <p>상품 정보를 데이터베이스에 추가하고 조회하는 기능을 수행합니다.</p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2025-03-19
 */
public class ProductRepositoryImpl implements ProductRepository {
    private final Connection connection = DBConnection.getConnection();
    private PreparedStatement pstmt = null;

    /**
     * 새로운 상품을 데이터베이스에 추가합니다.
     *
     * @param productDto 등록할 상품 정보를 포함한 {@link ProductDto} 객체
     * @return 상품 등록이 성공하면 {@code true}, 실패하면 {@code false}
     */
    @Override
    public boolean insertProduct(ProductDto productDto) {
        boolean isSuccess = false;
        String sql = "INSERT INTO product (prod_id, brand, prod_name, prod_price, prod_code, prod_category, prod_size) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, productDto.getProd_id());
            pstmt.setString(2, productDto.getBrand());
            pstmt.setString(3, productDto.getProd_name());
            pstmt.setInt(4, productDto.getProd_price());
            pstmt.setInt(5, productDto.getProd_code());
            pstmt.setString(6, productDto.getProd_category());
            pstmt.setBigDecimal(7, productDto.getProd_size());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 로그 처리 필요하면 Logger 사용
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isSuccess;
    }

    /**
     * 데이터베이스에서 모든 상품 목록을 조회합니다.
     *
     * @return {@link ProductDto} 객체 리스트, 조회된 상품이 없을 경우 빈 리스트 반환
     * @throws RuntimeException SQL 실행 중 예외 발생 시 예외 처리
     */
    @Override
    public List<ProductDto> getAllProduct() {
        List<ProductDto> productDtoList = new ArrayList<>();

        String sql = "SELECT * FROM product";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ProductDto productDto = new ProductDto(
                        rs.getString("prod_id"),
                        rs.getString("brand"),
                        rs.getString("prod_name"),
                        rs.getInt("prod_price"),
                        rs.getInt("prod_code"),
                        rs.getString("prod_category"),
                        rs.getBigDecimal("prod_size"));

                productDtoList.add(productDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return productDtoList;
    }
}
