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

}
