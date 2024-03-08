package Weflo.backend.service.deliverydetail;

import Weflo.backend.dto.deliveryDetail.response.ChangeDeliveryStatusResponse;
import Weflo.backend.dto.deliveryDetail.response.DeliveryDetailResponse;

public interface DeliveryDetailService {
    DeliveryDetailResponse getDeliveryDetails(Long orderHistoryId);
    ChangeDeliveryStatusResponse changeDeliveryStatus(Long orderHistoryId, String status);
}