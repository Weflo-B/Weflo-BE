package Weflo.backend.service.orderpart;

import Weflo.backend.domain.Drone;
import Weflo.backend.domain.OrderHistory;
import Weflo.backend.domain.Product;
import Weflo.backend.dto.common.AbnormalPartsDto;
import Weflo.backend.dto.common.OrderPartsDto;
import Weflo.backend.dto.common.ProductInfoDto;
import Weflo.backend.dto.part.response.AllOrderPartsResponse;
import Weflo.backend.dto.quotation.response.QuotationResponse;
import Weflo.backend.repository.drone.DroneRepository;
import Weflo.backend.repository.orderhistory.OrderHistoryRepository;
import Weflo.backend.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuotationDetailServiceImpl implements QuotationDetailService {
    private final DroneRepository droneRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final ProductRepository productRepository;
    private final OrderPartService orderPartService;

    @Override
    public QuotationResponse getQuotationDetailOfDrone(Long userId) {
        List<Drone> drones = droneRepository.findAllByUserId(userId);
        List<ProductInfoDto> productInfoDtos = new ArrayList<>();

        for (Drone drone : drones) {
            List<ProductInfoDto> productInfoDtosForDrone = orderPartService.createProductInfoDtoList(drone);
            productInfoDtos.addAll(productInfoDtosForDrone);
        }

        // 카테고리와 이름을 기준으로 ProductInfoDto 그룹화 및 합산가
        Map<String, Map<String, ProductInfoDto>> groupedProducts = productInfoDtos.stream()
                .collect(Collectors.groupingBy(
                        ProductInfoDto::getCategory,
                        Collectors.toMap(
                                ProductInfoDto::getName,
                                Function.identity(),
                                this::mergeProductInfoDtos
                        )
                ));

        // 카테고리별로 합산된 ProductInfoDto 리스트 생성
        List<ProductInfoDto> productsInfo = groupedProducts.values().stream()
                .flatMap(map -> map.values().stream())
                .collect(Collectors.toList());

        // 총 가격
        int sumPrice = productsInfo.stream()
                .mapToInt(ProductInfoDto::getTotalPrice)
                .sum();

        return QuotationResponse.of(productsInfo, sumPrice);
    }

    private ProductInfoDto mergeProductInfoDtos(ProductInfoDto a, ProductInfoDto b) {
        return ProductInfoDto.builder()
                .productImage(a.getProductImage())
                .category(a.getCategory())
                .name(a.getName())
                .price(a.getPrice())
                .salePrice(a.getSalePrice())
                .totalPrice(a.getTotalPrice() + b.getTotalPrice())
                .amount(a.getAmount() + b.getAmount())
                .build();
    }

    @Override
    public void confirmOrder(Long userId) {
//        orderPartService.saveProductAndOrderHistory(userId);
        List<Drone> findAllDroneByUserId = droneRepository.findAllByUserId(userId);
        for (Drone drone : findAllDroneByUserId) {
            List<OrderHistory> orderHistoryList = orderHistoryRepository.findByDrone(drone);
            for (OrderHistory orderHistory : orderHistoryList) {
                orderHistory.updateOrderHistoryStatus("배송준비중");
            }
        }
    }

}
