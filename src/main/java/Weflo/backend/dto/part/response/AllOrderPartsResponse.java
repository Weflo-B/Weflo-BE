package Weflo.backend.dto.part.response;

import Weflo.backend.dto.common.OrderPartsDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AllOrderPartsResponse {
    private List<OrderPartsDto> orderParts;

    public static AllOrderPartsResponse of(List<OrderPartsDto> orderPartsDtoList) {
        return builder()
                .orderParts(orderPartsDtoList)
                .build();
    }
}
