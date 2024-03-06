package Weflo.backend.dto.user.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsuranceResponse {
    private boolean isJoin;
    private LocalDate joinDate;
    private LocalDate updateDate;
    private int insuranceRate;
}