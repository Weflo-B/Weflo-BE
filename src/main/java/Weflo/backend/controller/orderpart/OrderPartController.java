package Weflo.backend.controller.orderpart;

import Weflo.backend.dto.part.response.AllOrderPartsResponse;
import Weflo.backend.dto.part.response.OrderPartsDetailResponse;
import Weflo.backend.global.ApiResponse;
import Weflo.backend.global.status.Message;
import Weflo.backend.service.orderpart.OrderPartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class OrderPartController {
    private final OrderPartService orderPartService;
//    private final OrderPartDetailService orderPartDetailService;

    @GetMapping("/orders/{userId}")
    public ApiResponse<AllOrderPartsResponse> getAllOrderParts(@PathVariable Long userId) {
        AllOrderPartsResponse allOrderParts = orderPartService.getAllOrderParts(userId);
        return ApiResponse.onSuccess(Message._GET_ALL_ORDER_PARTS_MESSAGE.getMessage(), allOrderParts);
    }

    @GetMapping("/orders/{userId}/{droneId}")
    public ApiResponse<OrderPartsDetailResponse> getOrderParts(@PathVariable Long userId,
                                                               @PathVariable Long droneId) {
        OrderPartsDetailResponse orderPartDetailOfDrone = orderPartService.getOrderPart(userId, droneId);
        return ApiResponse.onSuccess(Message._GET_ORDER_PARTS_DETAILS_MESSAGE.getMessage(), orderPartDetailOfDrone);
    }
}


