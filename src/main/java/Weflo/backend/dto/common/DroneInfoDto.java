package Weflo.backend.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DroneInfoDto {
    /*
    private Long id;
    private String nickname;
    private String productDate;
    private String modelName;
    private Integer balanceScore;
    private Integer totalScore;
    private Integer bladeScore;
    private Integer motorScore;
    private Integer escScore;
    private String droneStatus;
    private List<CheckHistoryDto> checkHistory;
    */
}
