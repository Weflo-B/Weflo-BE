package Weflo.backend.controller.insurance;

import Weflo.backend.dto.user.response.InsuranceResponse;
import Weflo.backend.global.ApiResponse;
import Weflo.backend.global.status.Message;
import Weflo.backend.service.insurance.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("join/{user_id}")
    public ApiResponse<InsuranceResponse> joinInsurance(@PathVariable("user_id") Long userId) {
        InsuranceResponse insuranceResponse = insuranceService.joinInsurance(userId);

        return ApiResponse.onSuccessCreated(Message._JOIN_SUCCESS_MESSAGE.getMessage(), insuranceResponse);
    }
}