package Weflo.backend.dto.part.response;

import Weflo.backend.dto.common.OrderPartsDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderPartsDetailResponse {
    private OrderPartsDto orderParts;
}
