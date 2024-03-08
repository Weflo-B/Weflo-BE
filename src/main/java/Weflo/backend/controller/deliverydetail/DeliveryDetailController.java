package Weflo.backend.controller.deliverydetail;

import Weflo.backend.dto.deliverydetail.response.ChangeDeliveryStatusResponse;
import Weflo.backend.dto.deliverydetail.response.DeliveryDetailResponse;
import Weflo.backend.global.ApiResponse;
import Weflo.backend.global.status.Message;
import Weflo.backend.service.deliverydetail.DeliveryDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/delivery/details")
public class DeliveryDetailController {
    private final DeliveryDetailService deliveryDetailService;

    @GetMapping("/{order_history_id}")
    public ApiResponse<DeliveryDetailResponse> getOrderHistories(@PathVariable("order_history_id") Long orderHistoryId) {

        DeliveryDetailResponse deliveryDetailResponse = deliveryDetailService.getDeliveryDetails(orderHistoryId);

        return ApiResponse.onSuccess(Message._GET_DELIVERY_DETAILS_MESSAGE.getMessage(), deliveryDetailResponse);
    }

    @PatchMapping()
    public ApiResponse<ChangeDeliveryStatusResponse> changeDeliveryStatus(
            @RequestParam("order_history_id") Long orderHistoryId,
            @RequestParam("status") String status) {

        ChangeDeliveryStatusResponse changeDeliveryStatusResponse = deliveryDetailService.changeDeliveryStatus(orderHistoryId, status);

        return ApiResponse.onSuccess(Message._CHANGE_DELIVERY_STATUS_MESSAGE.getMessage(), changeDeliveryStatusResponse);
    }
}
