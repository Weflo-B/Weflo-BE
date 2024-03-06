package Weflo.backend.dto.part.response;

import Weflo.backend.dto.common.OrderPartsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderPartsDetailResponse {
    private OrderPartsDto orderParts;
}
