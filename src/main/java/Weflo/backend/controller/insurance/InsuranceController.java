package Weflo.backend.controller.insurance;

import Weflo.backend.dto.user.response.InsuranceResponse;
import Weflo.backend.global.ApiResponse;
import Weflo.backend.global.status.Message;
import Weflo.backend.service.insurance.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/insurance")
public class InsuranceController {
    private final InsuranceService insuranceService;
    @GetMapping("/{user_id}")
    public ApiResponse<InsuranceResponse> getInsuranceDetails(@PathVariable("user_id") Long userId) {
        InsuranceResponse insuranceResponse = insuranceService.getInsuranceDetails(userId);

        return ApiResponse.onSuccess(Message._GET_INSURANCE_MESSAGE.getMessage(), insuranceResponse);
    }
}