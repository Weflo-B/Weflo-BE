package Weflo.backend.dto.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuyerInfoDto {
    private String name;
    private String tel;
    private String address;
}