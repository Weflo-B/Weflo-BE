package Weflo.backend.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartScoreDto {
    private String name;
//    private Integer motorScore;
//    private Integer bladeScore;
//    private Integer escScore;
    private Integer score;
//    private Integer totalAvg;
//    private AbnormalCountDto abnormalCount;

//    public static PartScoreDto of(String name, Integer motorScore, Integer bladeScore, Integer escScore) {
//        return PartScoreDto.builder()
//                .name(name)
//                .motorScore(motorScore)
//                .bladeScore(bladeScore)
//                .escScore(escScore)
//                .build();
//    }
    public static PartScoreDto of(String name, Integer score) {
        return PartScoreDto.builder()
                .name(name)
                .score(score)
                .build();
    }

}
