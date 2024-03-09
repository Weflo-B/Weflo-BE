package Weflo.backend.service.orderpart;

import Weflo.backend.domain.Drone;
import Weflo.backend.domain.OrderHistory;
import Weflo.backend.domain.Product;
import Weflo.backend.dto.common.AbnormalPartsDto;
import Weflo.backend.dto.common.ProductInfoDto;
import Weflo.backend.dto.part.response.AllOrderPartsResponse;
import Weflo.backend.dto.part.response.OrderPartsDetailResponse;

import java.util.List;

public interface OrderPartService {
    AllOrderPartsResponse getAllOrderParts(Long userId);

    OrderPartsDetailResponse getOrderPart(Long userId, Long droneId);

    AllOrderPartsResponse saveProductAndOrderHistory(Long userId);

    OrderHistory createOrderHistoryForDrone(Drone drone, Product product, int amount, int totalPrice);

    List<AbnormalPartsDto> getAbnormalParts(Drone drone);

    List<ProductInfoDto> createProductInfoDtoList(Drone drone);

}
