package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutboundDto {
    private String outbound_id;
    private String prod_id;
    private String client_id;
    private int quantity;
    private int outbound_status;
    private Date req_outbound_day;
    private String ware_id;
}
