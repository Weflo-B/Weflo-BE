package Weflo.backend.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AbnormalCountDto {
    /*
    private Integer motorCount;
    private Integer bladeCount;
    private Integer escCount;
    */
}