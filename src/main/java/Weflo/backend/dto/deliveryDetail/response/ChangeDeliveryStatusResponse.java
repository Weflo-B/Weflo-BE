package Weflo.backend.dto.deliveryDetail.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ChangeDeliveryStatusResponse {
    private String deliveryDetailStatus;
}
