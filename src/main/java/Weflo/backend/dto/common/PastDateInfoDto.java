package Weflo.backend.dto.common;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PastDateInfoDto {
    private String status;
    private LocalDate date;
}
