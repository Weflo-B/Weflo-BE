package Weflo.backend.dto.quotation.response;

import Weflo.backend.dto.common.ProductInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class QuotationResponse {
    private String name;
    private LocalDate orderDate;
    private List<ProductInfoDto> productsInfo;
    private int sumPrice;
}
