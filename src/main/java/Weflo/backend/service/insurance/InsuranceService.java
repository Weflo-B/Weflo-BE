package Weflo.backend.service.insurance;

import Weflo.backend.dto.user.response.InsuranceResponse;

public interface InsuranceService {
    InsuranceResponse getInsuranceDetails(Long userId);
    InsuranceResponse joinInsurance(Long userId);
}