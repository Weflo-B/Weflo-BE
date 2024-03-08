package Weflo.backend.service.orderHistory;

import Weflo.backend.dto.orderHistory.response.AllOrderHistoriesResponse;

public interface OrderHistoryService {
    AllOrderHistoriesResponse getOrderHistories(Long userId, Integer month, String orderStatus);
}