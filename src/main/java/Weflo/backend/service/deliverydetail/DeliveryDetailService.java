package Weflo.backend.service.deliverydetail;

import Weflo.backend.dto.deliveryDetail.response.DeliveryDetailResponse;

public interface DeliveryDetailService {
    DeliveryDetailResponse getDeliveryDetails(Long orderHistoryId);
}