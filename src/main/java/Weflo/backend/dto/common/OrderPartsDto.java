package Weflo.backend.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderPartsDto {
    private Long id;
    private String nickname;
    private Integer balanceScore;
    private Integer totalScore;
    private LocalDate orderDate;
    private LocalDate estimateDate;
    private List<ProductInfoDto> productsInfo;
    private List<AbnormalPartsDto> abnormalities;
}
