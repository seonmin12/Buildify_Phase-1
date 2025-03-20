package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    private String adminNumber;
    private String adminRole;
    private String adminName;
    private String adminEmail;
    private Date adminEnterday;
    private String adminAddress;
    private String adminPhone;
    private String adminId;
    private String adminPw;

}
