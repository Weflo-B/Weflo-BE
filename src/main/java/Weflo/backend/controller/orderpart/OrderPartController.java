package Weflo.backend.controller.orderpart;

import Weflo.backend.dto.part.response.AllOrderPartsResponse;
import Weflo.backend.global.ApiResponse;
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

    @GetMapping("/orders/{userId}")
    public ApiResponse<AllOrderPartsResponse> getAllOrderParts(@PathVariable Long userId) {
        AllOrderPartsResponse allOrderParts = orderPartService.getAllOrderParts(userId);
        return ApiResponse.onSuccess(allOrderParts);
    }
}
