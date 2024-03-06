package Weflo.backend.dto.deliveryDetail.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DeliveryDetailResponse {
    private LocalDate orderDate;
    private LocalDate estimateDate;
    private String deliveryDetailStatus;
    private String category;
    private String name;
    private int salePrice;
    private int amount;
}
