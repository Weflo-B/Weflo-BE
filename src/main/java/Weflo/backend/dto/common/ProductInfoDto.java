package Weflo.backend.dto.common;

import Weflo.backend.domain.OrderHistory;
import Weflo.backend.domain.Product;
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

    public static ProductInfoDto of(Product product, OrderHistory orderHistory) {
        return ProductInfoDto.builder()
                .productImage(product.getProductImage())
                .category(product.getCategory())
                .name(product.getName())
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .totalPrice(orderHistory.getTotalPrice())
                .amount(orderHistory.getAmount())
                .build();
    }
}
