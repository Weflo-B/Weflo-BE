package Weflo.backend.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderHistoryDto {
    private Long id;
    private String category;
    private String name;
    private Integer salePrice;
    private Integer amount;
    private LocalDate orderDate;
    private String status;
    private String productImage;
}
