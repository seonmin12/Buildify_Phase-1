package domain.DH_UserManagement.repository;

import config.DBConnection;
import dto.ProductDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    Connection connection = DBConnection.getConnection();
    CallableStatement cs = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

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

    @Override
    public List<ProductDto> getAllProduct() {
        List<ProductDto> productDtoList = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        CallableStatement cs = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        String sql = "SELECT * FROM  product";

        try {
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

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
