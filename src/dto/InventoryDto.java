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

public class InventoryDto {
    private String prod_id;
    private String client_id;
    private int quantity;
    private String ware_id;
    private String prod_name;

    private Date last_inbound_day;
    private Date last_outbount_day; // To-do : outbount->outbound로 오타 수정





}
