package Weflo.backend.dto.part.response;

import Weflo.backend.dto.common.OrderPartsDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderPartsDetailResponse {
    private OrderPartsDto orderParts;

    public static OrderPartsDetailResponse of(OrderPartsDto orderPartsDto) {
        return builder()
                .orderParts(orderPartsDto)
                .build();
    }
}
