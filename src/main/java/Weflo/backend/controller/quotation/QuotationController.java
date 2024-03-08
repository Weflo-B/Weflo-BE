package Weflo.backend.controller.quotation;

import Weflo.backend.dto.quotation.response.QuotationResponse;
import Weflo.backend.global.ApiResponse;
import Weflo.backend.global.status.Message;
import Weflo.backend.service.orderpart.QuotationDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class QuotationController {
    private final QuotationDetailService quotationDetailService;

    @GetMapping("/quotation/{userId}")
    public ApiResponse<QuotationResponse> getQuotation(@PathVariable Long userId) {
        return ApiResponse.onSuccess(Message._GET_QUOTATION_MESSAGE.getMessage(), quotationDetailService.getQuotationDetailOfDrone(userId));
    }

    @PatchMapping("/orders/{userId}")
    public ApiResponse<String> confirmOrder(@PathVariable Long userId) {
        quotationDetailService.confirmOrder(userId);
        return ApiResponse.onSuccess(Message._ORDER_SUCCESS_MESSAGE.getMessage());
    }

}
