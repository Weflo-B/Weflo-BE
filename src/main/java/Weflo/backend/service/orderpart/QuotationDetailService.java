package Weflo.backend.service.orderpart;

import Weflo.backend.dto.part.response.OrderPartsDetailResponse;
import Weflo.backend.dto.quotation.response.OrderConfirmResponse;
import Weflo.backend.dto.quotation.response.QuotationResponse;

import java.util.List;

public interface QuotationDetailService {
    QuotationResponse getQuotationDetailOfDrone(Long userId);

    void confirmOrder(Long userId);
}
