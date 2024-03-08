package Weflo.backend.dto.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SellerInfoDto {
    private String deliveryCompany;
    private String deliveryTel;
    private String invoiceNumber;
    private String seller;
}