package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

public class WarehouseDto {

    private String ware_id;
    private String client_id;
    private String prod_id;
    private int quantity;
    private Date last_inbound_day;
    private Date last_outbount_day;



}
