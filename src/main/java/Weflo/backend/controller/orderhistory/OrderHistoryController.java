package Weflo.backend.controller.orderhistory;

import Weflo.backend.dto.orderHistory.response.AllOrderHistoriesResponse;
import Weflo.backend.global.ApiResponse;
import Weflo.backend.global.status.Message;
import Weflo.backend.service.orderHistory.OrderHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/histories")
public class OrderHistoryController {
    private final OrderHistoryService orderHistoryService;

    @GetMapping
    public ApiResponse<AllOrderHistoriesResponse> getOrderHistories(
            @RequestParam("user_id") Long userId,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "order_status", required = false) String orderStatus) {

        AllOrderHistoriesResponse allOrderHistoriesResponse = orderHistoryService.getOrderHistories(userId, month, orderStatus);

        return ApiResponse.onSuccess(Message._GET_ALL_ORDER_HISTORIES_MESSAGE.getMessage(), allOrderHistoriesResponse);
    }
}