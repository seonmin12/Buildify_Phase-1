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
public class ProductDto {

    private String prod_id;
    private String brand;
    private String prod_name;
    private int prod_price;
    private int prod_code;
    private String prod_category;
    private BigDecimal prod_size;


}