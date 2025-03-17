package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String client_id;
    private String user_name;
    private String user_phone;
    private String user_email;
    private String user_adress;
    private String business_number;
    private Date user_enterday;
    private String user_id;
    private String user_pw;
    private int user_status;
    private BigDecimal user_ware_size;
    private BigDecimal user_ware_use;

}
