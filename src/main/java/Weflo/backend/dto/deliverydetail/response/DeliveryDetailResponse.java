package Weflo.backend.dto.deliverydetail.response;

import Weflo.backend.dto.common.BuyerInfoDto;
import Weflo.backend.dto.common.PastDateInfoDto;
import Weflo.backend.dto.common.ProductInfoDto;
import Weflo.backend.dto.common.SellerInfoDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class DeliveryDetailResponse {
    private LocalDate orderDate;
    private LocalDate estimateDate;
    private String currentStatus;
    private List<PastDateInfoDto> pastDates;
    private ProductInfoDto productInfos;
    private SellerInfoDto sellerInfos;
    private BuyerInfoDto buyerInfos;
}
