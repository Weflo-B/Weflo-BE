package Weflo.backend.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductInfoDto {
    private String productImage;
    private String category;
    private String name;
    private Integer price;
    private Integer salePrice;
    private Integer totalPrice;
    private Integer amount;
}
