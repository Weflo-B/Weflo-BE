package Weflo.backend.service.orderhistory;

import Weflo.backend.dto.orderhistory.response.AllOrderHistoriesResponse;

public interface OrderHistoryService {
    AllOrderHistoriesResponse getOrderHistories(Long userId, Integer month, String orderStatus);
}