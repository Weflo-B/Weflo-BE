package Weflo.backend.controller.quotation;

import Weflo.backend.dto.quotation.response.QuotationResponse;
import Weflo.backend.global.ApiResponse;
import Weflo.backend.service.orderpart.OrderPartService;
import Weflo.backend.service.orderpart.QuotationDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class QuotationController {
    //    private final OrderPartService orderPartService;
    private final QuotationDetailService quotationDetailService;

    @GetMapping("/quotation/{user_id}")
    public ApiResponse<QuotationResponse> getQuotation(@PathVariable Long user_id) {
        return ApiResponse.onSuccess(quotationDetailService.getQuotationDetailOfDrone(user_id));
    }
}
