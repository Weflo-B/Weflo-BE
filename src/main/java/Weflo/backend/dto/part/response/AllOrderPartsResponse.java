package Weflo.backend.dto.part.response;

import Weflo.backend.dto.common.OrderPartsDto;
import Weflo.backend.dto.common.ProductInfoDto;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
public class AllOrderPartsResponse {
    private List<OrderPartsDto> orderParts;
    private Integer totalOrderCount;

    public static AllOrderPartsResponse of(List<OrderPartsDto> orderPartsDtoList) {
        int totalOrderCount = orderPartsDtoList.stream()
                .mapToInt(orderPartsDto -> orderPartsDto.getProductsInfo().stream()
                        .mapToInt(ProductInfoDto::getAmount).sum()).sum();

        return builder()
                .orderParts(orderPartsDtoList)
                .totalOrderCount(totalOrderCount)
                .build();
    }
}
