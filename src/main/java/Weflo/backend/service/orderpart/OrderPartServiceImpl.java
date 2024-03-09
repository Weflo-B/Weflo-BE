package Weflo.backend.service.orderpart;

import Weflo.backend.domain.Drone;
import Weflo.backend.domain.OrderHistory;
import Weflo.backend.domain.Part;
import Weflo.backend.domain.Product;
import Weflo.backend.dto.common.AbnormalPartsDto;
import Weflo.backend.dto.common.OrderPartsDto;
import Weflo.backend.dto.common.PartScoreDto;
import Weflo.backend.dto.common.ProductInfoDto;
import Weflo.backend.dto.part.response.AllOrderPartsResponse;
import Weflo.backend.dto.part.response.OrderPartsDetailResponse;
import Weflo.backend.repository.drone.DroneRepository;
import Weflo.backend.repository.orderhistory.OrderHistoryRepository;
import Weflo.backend.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public List<ProductInfoDto> createProductInfoDtoList(Drone drone) {
        Integer lowBladeCount = countLowBladeScores(drone.getId());
        Integer lowMotorCount = countLowMotorScores(drone.getId());
        Integer lowEscCount = countLowEscScores(drone.getId());

        drone.setCount(lowBladeCount, lowMotorCount, lowEscCount);
        droneRepository.save(drone);

        ProductInfoDto bladeInfo = ProductInfoDto.builder()
                .productImage("https://weflo.s3.ap-northeast-2.amazonaws.com/1.jpg")
                .estimateDate(LocalDate.now().plusDays(5))
                .category("Blade")
                .name("HOBBYWING X11모터용 블레이드 4314 CW")
                .price(60000)
                .salePrice(54000)
                .amount((Integer) lowBladeCount)
                .totalPrice((Integer) (lowBladeCount * 54000))
                .build();

        ProductInfoDto motorInfo = ProductInfoDto.builder()
                .productImage("https://weflo.s3.ap-northeast-2.amazonaws.com/7.jpg")
                .estimateDate(LocalDate.now().plusDays(5))
                .category("Motor")
                .name("T2212-920KV 브러쉬리스 모터 CW (V2)")
                .price(14200)
                .salePrice(12000)
                .amount((Integer) lowMotorCount)
                .totalPrice((Integer) (lowMotorCount * 12000))
                .build();

        ProductInfoDto escInfo = ProductInfoDto.builder()
                .productImage("https://weflo.s3.ap-northeast-2.amazonaws.com/11.png")
                .estimateDate(LocalDate.now().plusDays(5))
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
        List<OrderPartsDto> orderPartsDtos = new ArrayList<>();

        for (Drone drone : drones) {
            List<AbnormalPartsDto> abnormalPartsDtosForDrone = getAbnormalParts(drone);

            List<ProductInfoDto> productInfoDtosForDrone = createProductInfoDtoList(drone);

//            productInfoDtosForDrone.forEach(productInfoDto -> {
//                Product product = productRepository.save(Product.builder()
//                        .category(productInfoDto.getCategory())
//                        .name(productInfoDto.getName())
//                        .price(productInfoDto.getPrice())
//                        .salePrice(productInfoDto.getSalePrice())
//                        .productImage(productInfoDto.getProductImage())
//                        .build());

                productInfoDtosForDrone.forEach(productInfoDto -> {
                    Product product = Product.builder()
                            .category(productInfoDto.getCategory())
                            .name(productInfoDto.getName())
                            .price(productInfoDto.getPrice())
                            .salePrice(productInfoDto.getSalePrice())
                            .productImage(productInfoDto.getProductImage())
                            .build();
                OrderHistory orderHistory = createOrderHistoryForDrone(drone, product, productInfoDto.getAmount(), productInfoDto.getTotalPrice());
//                orderHistoryRepository.save(orderHistory);
            });



//            OrderPartsDto orderPartsDto = OrderPartsDto.of(drone, productInfoDtosForDrone, abnormalPartsDtosForDrone);
            // 마지막으로, 각 드론에 대한 OrderPartsDto 객체를 생성하고 리스트에 추가합니다.
            OrderPartsDto orderPartsDto = OrderPartsDto.builder()
                    .id(drone.getId())
                    .droneImg(drone.getDroneImage())
                    .nickname(drone.getNickname())
                    .balanceScore(drone.getCheckHistory().getBalanceScore())
                    .totalScore(drone.getCheckHistory().getTotalScore())
                    .orderDate(LocalDate.now())
//                    .estimateDate(LocalDate.now().plusDays(3))
                    .productsInfo(productInfoDtosForDrone)
//                    .abnormalities(abnormalPartsDtosForDrone)
                    .build();

            orderPartsDtos.add(orderPartsDto);
        }

        return AllOrderPartsResponse.of(orderPartsDtos);
    }

    @Override
    public AllOrderPartsResponse saveProductAndOrderHistory(Long userId) {
        List<Drone> drones = droneRepository.findAllByUserId(userId);
        List<OrderPartsDto> orderPartsDtos = new ArrayList<>();

        for (Drone drone : drones) {
            List<ProductInfoDto> productInfoDtosForDrone = createProductInfoDtoList(drone);

            productInfoDtosForDrone.forEach(productInfoDto -> {
                Product product = productRepository.save(Product.builder()
                        .category(productInfoDto.getCategory())
                        .name(productInfoDto.getName())
                        .price(productInfoDto.getPrice())
                        .salePrice(productInfoDto.getSalePrice())
                        .productImage(productInfoDto.getProductImage())
                        .build());

                OrderHistory orderHistory = createOrderHistoryForDrone(drone, product, productInfoDto.getAmount(), productInfoDto.getTotalPrice());
                orderHistoryRepository.save(orderHistory);
            });

            //            OrderPartsDto orderPartsDto = OrderPartsDto.of(drone, productInfoDtosForDrone, abnormalPartsDtosForDrone);
            OrderPartsDto orderPartsDto = OrderPartsDto.builder()
                    .id(drone.getId())
                    .droneImg(drone.getDroneImage())
                    .nickname(drone.getNickname())
                    .balanceScore(drone.getCheckHistory().getBalanceScore())
                    .totalScore(drone.getCheckHistory().getTotalScore())
                    .orderDate(LocalDate.now())
//                    .estimateDate(LocalDate.now().plusDays(3))
                    .productsInfo(productInfoDtosForDrone)
//                    .abnormalities(abnormalPartsDtosForDrone)
                    .build();

            orderPartsDtos.add(orderPartsDto);
        }

        return AllOrderPartsResponse.of(orderPartsDtos);
    }


    @Override
    public OrderPartsDetailResponse getOrderPart(Long userId, Long droneId) {
        List<Drone> drones = droneRepository.findAllByUserId(userId);
        Drone findDrone = null;
        for (Drone drone : drones) {
            if (droneId == drone.getId()) {
                findDrone = drone;
            }
        }
        List<AbnormalPartsDto> abnormalPartsDtosForDrone = getAbnormalParts(findDrone);
        List<ProductInfoDto> productInfoDtosForDrone = createProductInfoDtoList(findDrone);

        OrderPartsDto orderPartsDto = OrderPartsDto.of(findDrone, productInfoDtosForDrone, abnormalPartsDtosForDrone);

//        OrderPartsDto orderPartsDto = OrderPartsDto.builder()
//                .id(findDrone.getId())
//                .droneImg(findDrone.getDroneImage())
//                .nickname(findDrone.getNickname())
//                .balanceScore(findDrone.getCheckHistory().getBalanceScore())
//                .totalScore(findDrone.getCheckHistory().getTotalScore())
//                .orderDate(LocalDate.now()) // 마지막 주문 날짜로 설정해야 할 수도 있습니다.
//                .estimateDate(LocalDate.now().plusDays(3)) // 추정 배송 날짜는 비즈니스 로직에 따라 조정해야 할 수 있습니다.
//                .productsInfo(productInfoDtosForDrone)
//                .abnormalities(abnormalPartsDtosForDrone)
//                .build();

        return OrderPartsDetailResponse.of(orderPartsDto);

    }

    public List<AbnormalPartsDto> getAbnormalParts(Drone drone) {
        Map<String, List<PartScoreDto>> categoryToAbnormalPartsMap = new LinkedHashMap<>();
        categoryToAbnormalPartsMap.put("Blade", new ArrayList<>());
        categoryToAbnormalPartsMap.put("Motor", new ArrayList<>());
        categoryToAbnormalPartsMap.put("Esc", new ArrayList<>());

        for (Part part : drone.getParts()) {
            if (part.getMotorScore() != null && part.getMotorScore() <= 70) {
                categoryToAbnormalPartsMap.get("Blade")
                        .add(PartScoreDto.of(part.getName(), part.getMotorScore()));
//                        .add(PartScoreDto.of(part.getName(), part.getMotorScore(), null, null))
            }
            if (part.getBladeScore() != null && part.getBladeScore() <= 70) {
                categoryToAbnormalPartsMap.get("Motor")
                        .add(PartScoreDto.of(part.getName(), part.getMotorScore()));
            }
            if (part.getEscScore() != null && part.getEscScore() <= 70) {
                categoryToAbnormalPartsMap.get("Esc")
                        .add(PartScoreDto.of(part.getName(), part.getMotorScore()));
            }
        }

        // value가 없는 entry를 제거
        categoryToAbnormalPartsMap.entrySet().removeIf(entry -> entry.getValue().isEmpty());

        return categoryToAbnormalPartsMap.entrySet().stream()
                .map(entry -> AbnormalPartsDto.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public OrderHistory createOrderHistoryForDrone(Drone drone, Product product, int amount, int totalPrice) {
        return OrderHistory.builder()
                .drone(drone)
                .product(product)
                .amount(amount)
                .totalPrice(totalPrice)
                .orderName(drone.getUser().getName())
                .orderDate(LocalDate.now())
                .orderHistoryStatus("주문대기중")
                .build();
    }
}
