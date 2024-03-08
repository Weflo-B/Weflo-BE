package Weflo.backend.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductInfoListDto {
    private ProductInfoDto bladeInfoDto;
    private ProductInfoDto motorInfoDto;
    private ProductInfoDto escInfoDto;

    public static ProductInfoListDto of(ProductInfoDto bladeInfoDto, ProductInfoDto motorInfoDto, ProductInfoDto escInfoDto) {
//        this.bladeInfoDto = bladeInfoDto;
//        this.motorInfoDto = motorInfoDto;
//        this.escInfoDto = escInfoDto;
        return ProductInfoListDto.builder()
                .bladeInfoDto(bladeInfoDto)
                .motorInfoDto(motorInfoDto)
                .escInfoDto(escInfoDto)
                .build();

    }
}
