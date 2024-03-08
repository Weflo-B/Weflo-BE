package Weflo.backend.service.orderpart;

import Weflo.backend.dto.part.response.AllOrderPartsResponse;
import Weflo.backend.dto.part.response.OrderPartsDetailResponse;

public interface OrderPartService {
    AllOrderPartsResponse getAllOrderParts(Long userId);

    OrderPartsDetailResponse getOrderPart(Long userId, Long droneId);

}
