package Weflo.backend.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Data;

@Data
@Builder
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartScoreDto {
    private String name;
    private Integer motorScore;
    private Integer bladeScore;
    private Integer escScore;
    private Integer totalAvg;
    private AbnormalCountDto abnormalCount;
}
