package Weflo.backend.service.orderpart;

import Weflo.backend.dto.quotation.response.QuotationResponse;


public interface QuotationDetailService {
    QuotationResponse getQuotationDetailOfDrone(Long userId);

    void confirmOrder(Long userId);
}
