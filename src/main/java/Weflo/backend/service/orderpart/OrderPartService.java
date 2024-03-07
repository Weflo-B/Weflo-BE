package Weflo.backend.service.orderpart;

import Weflo.backend.dto.part.response.AllOrderPartsResponse;

public interface OrderPartService {
    AllOrderPartsResponse getAllOrderParts(Long userId);

}
