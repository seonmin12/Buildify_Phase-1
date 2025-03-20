package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqOutboundDto {
    private String prodId;
    private String brand;
    private String prodName;
    private Integer prodPrice;
    private Integer prodCode;
    private String prodCategory;
    private BigDecimal prodSize;
    private Integer quantity;
    private String clientId;
    private String wareId;

    @Override
    public String toString() {
        return String.format("브랜드: %s | 상품명: %s | 가격: %,d원 | 카테고리: %s | 크기: %.2f | 재고 수량: %d",
                brand, prodName, prodPrice, prodCategory, prodSize, quantity);
    }

}
