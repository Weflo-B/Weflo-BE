package Weflo.backend.service.deliverydetail;

import Weflo.backend.dto.deliverydetail.response.ChangeDeliveryStatusResponse;
import Weflo.backend.dto.deliverydetail.response.DeliveryDetailResponse;

public interface DeliveryDetailService {
    DeliveryDetailResponse getDeliveryDetails(Long orderHistoryId);
    ChangeDeliveryStatusResponse changeDeliveryStatus(Long orderHistoryId, String status);
}