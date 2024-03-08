package Weflo.backend.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AbnormalPartsDto {
    private String category;
    private List<PartScoreDto> partsScore;

    public static AbnormalPartsDto of(String category, List<PartScoreDto> partsScoreList) {
        return AbnormalPartsDto.builder()
                .category(category)
                .partsScore(partsScoreList)
                .build();

    }
}
