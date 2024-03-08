package Weflo.backend.dto.deliverydetail.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeDeliveryStatusResponse {
    private String deliveryDetailStatus;
}
