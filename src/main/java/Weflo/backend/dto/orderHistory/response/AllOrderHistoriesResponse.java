package Weflo.backend.dto.orderHistory.response;

import Weflo.backend.dto.common.OrderHistoryDto;
import Weflo.backend.dto.common.OrderStatusDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AllOrderHistoriesResponse {
    private Integer sumPrice;
    private List<OrderStatusDto> orderStatuses;
    private List<OrderHistoryDto> orderHistories;
}
