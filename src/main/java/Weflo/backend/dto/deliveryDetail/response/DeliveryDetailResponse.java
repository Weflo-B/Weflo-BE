package Weflo.backend.dto.deliveryDetail.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class DeliveryDetailResponse {
    private LocalDate orderDate;
    private LocalDate estimateDate;
    private String deliveryStatus;
    private String category;
    private String name;
    private int salePrice;
    private int amount;
}
