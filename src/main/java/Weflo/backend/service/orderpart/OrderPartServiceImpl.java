package Weflo.backend.service.orderpart;

import Weflo.backend.domain.Drone;
import Weflo.backend.domain.OrderHistory;
import Weflo.backend.domain.Part;
import Weflo.backend.domain.Product;
import Weflo.backend.dto.common.OrderPartsDto;
import Weflo.backend.dto.common.ProductInfoDto;
import Weflo.backend.dto.part.response.AllOrderPartsResponse;
import Weflo.backend.repository.drone.DroneRepository;
import Weflo.backend.repository.orderhistory.OrderHistoryRepository;
import Weflo.backend.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderPartServiceImpl implements OrderPartService {
    private final DroneRepository droneRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final ProductRepository productRepository;

    public Integer countLowBladeScores(Long droneId) {
        List<Part> parts = droneRepository.findById(droneId).orElseThrow(() -> new RuntimeException("Drone not found")).getParts();
        return (Integer) Math.toIntExact(parts.stream()
                .filter(part -> part.getBladeScore() != null && part.getBladeScore() <= 70)
                .count());
    }

    public Integer countLowMotorScores(Long droneId) {
        List<Part> parts = droneRepository.findById(droneId).orElseThrow(() -> new RuntimeException("Drone not found")).getParts();
        return (Integer) Math.toIntExact(parts.stream()
                .filter(part -> part.getMotorScore() != null && part.getMotorScore() <= 70)
                .count());
    }

    public Integer countLowEscScores(Long droneId) {
        List<Part> parts = droneRepository.findById(droneId).orElseThrow(() -> new RuntimeException("Drone not found")).getParts();
        return (Integer) Math.toIntExact(parts.stream()
                .filter(part -> part.getEscScore() != null && part.getEscScore() <= 70)
                .count());
    }

    private Long getProductIdBasedOnCategory(String category) {
        switch (category) {
            case "Blade":
                return 1L; // 블레이드의 경우 ID 1
            case "Motor":
                return 7L; // 모터의 경우 ID 7
            case "Esc":
                return 12L; // ESC의 경우 ID 12
            default:
                throw new RuntimeException("Invalid category");
        }
    }

    public List<ProductInfoDto> createProductInfoDtoList(Drone drone) {
        Integer lowBladeCount = countLowBladeScores(drone.getId());
        Integer lowMotorCount = countLowMotorScores(drone.getId());
        Integer lowEscCount = countLowEscScores(drone.getId());

        drone.setCount(lowBladeCount, lowMotorCount, lowEscCount);
        droneRepository.save(drone);

        ProductInfoDto bladeInfo = ProductInfoDto.builder()
                .productImage("https://weflo.s3.ap-northeast-2.amazonaws.com/1.jpg")
                .category("Blade")
                .name("HOBBYWING X11모터용 블레이드 4314 CW")
                .price(60000)
                .salePrice(54000)
                .amount((Integer) lowBladeCount)
                .totalPrice((Integer) (lowBladeCount * 54000))
                .build();

        ProductInfoDto motorInfo = ProductInfoDto.builder()
                .productImage("https://weflo.s3.ap-northeast-2.amazonaws.com/7.jpg")
                .category("Motor")
                .name("T2212-920KV 브러쉬리스 모터 CW (V2)")
                .price(14200)
                .salePrice(12000)
                .amount((Integer) lowMotorCount)
                .totalPrice((Integer) (lowMotorCount * 12000))
                .build();

        ProductInfoDto escInfo = ProductInfoDto.builder()
                .productImage("https://weflo.s3.ap-northeast-2.amazonaws.com/11.png")
                .category("Esc")
                .name("[T-MOTOR] ALPHA 120A 12S 변속기")
                .price(253000)
                .salePrice(227700)
                .amount((Integer) lowEscCount)
                .totalPrice((Integer) (lowEscCount * 227700))
                .build();

        return List.of(bladeInfo, motorInfo, escInfo);
    }

    @Override
    @Transactional
    public AllOrderPartsResponse getAllOrderParts(Long userId) {
        List<Drone> drones = droneRepository.findAllByUserId(userId);
//        List<AllOrderPartsResponse> allOrderPartsResponses = new ArrayList<>();
        List<OrderPartsDto> orderPartsDtos = new ArrayList<>();

        for (Drone drone : drones) {
            List<ProductInfoDto> productInfoDtos = createProductInfoDtoList(drone);

            for (ProductInfoDto productInfoDto : productInfoDtos) {
                Long productId = getProductIdBasedOnCategory(productInfoDto.getCategory());

                Product product = productRepository.save(Product.builder()
                        .id(productId)
                        .salePrice(productInfoDto.getSalePrice())
                        .price(productInfoDto.getPrice())
                        .category(productInfoDto.getCategory())
                        .productImage(productInfoDto.getProductImage())
                        .name(productInfoDto.getName())
                        .build());

                OrderHistory orderHistory = createOrderHistoryForDrone(drone, product, productInfoDto.getAmount(), productInfoDto.getTotalPrice());
                orderHistoryRepository.save(orderHistory);
                orderPartsDtos.add(OrderPartsDto.of(orderHistory, List.of(productInfoDto)));
            }
        }
        return AllOrderPartsResponse.of(orderPartsDtos);
    }

    private OrderHistory createOrderHistoryForDrone(Drone drone, Product product, int amount, int totalPrice) {
        return OrderHistory.builder()
                .drone(drone)
                .product(product)
                .amount(amount)
                .totalPrice(totalPrice)
                .orderName("파블로 항공")
                .orderDate(LocalDate.now())
                .orderHistoryStatus("Pending")
                .build();
    }
}
