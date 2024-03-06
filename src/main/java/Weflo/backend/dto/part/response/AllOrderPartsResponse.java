package Weflo.backend.dto.part.response;

import Weflo.backend.dto.common.OrderPartsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AllOrderPartsResponse {
    private List<OrderPartsDto> orderParts;
}
