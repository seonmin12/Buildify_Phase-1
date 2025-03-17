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
    private int capacity; // 창고의 가용량, 현재 사용 가능한 양
    private Date last_inbound_day;
    private Date last_outbount_day;



}
