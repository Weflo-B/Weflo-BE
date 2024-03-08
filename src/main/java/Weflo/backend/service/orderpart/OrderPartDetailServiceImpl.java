//package Weflo.backend.service.orderpart;
//
//import Weflo.backend.domain.Drone;
//import Weflo.backend.domain.OrderHistory;
//import Weflo.backend.domain.Part;
//import Weflo.backend.domain.Product;
//import Weflo.backend.dto.common.AbnormalPartsDto;
//import Weflo.backend.dto.common.OrderPartsDto;
//import Weflo.backend.dto.common.PartScoreDto;
//import Weflo.backend.dto.common.ProductInfoDto;
//import Weflo.backend.dto.part.response.OrderPartsDetailResponse;
//import Weflo.backend.repository.drone.DroneRepository;
//import Weflo.backend.repository.orderhistory.OrderHistoryRepository;
//import Weflo.backend.repository.orderpart.OrderPartRepository;
//import Weflo.backend.repository.product.ProductRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class OrderPartDetailServiceImpl implements OrderPartDetailService {
//    private final DroneRepository droneRepository;
//    private final OrderHistoryRepository orderHistoryRepository;
//    private final ProductRepository productRepository;
//
//
//
//    @Override
//    public OrderPartsDetailResponse getOrderPartDetailOfDrone(Long userId, Long droneId) {
//        Drone drone = droneRepository.findByIdAndUserId(droneId, userId)
//                .orElseThrow(() -> new RuntimeException("Drone not found for the given user and ID"));
//
//        // 이상 부품들의 카테고리별로 매핑하기 위한 Map 생성
//        Map<String, List<PartScoreDto>> abnormalPartsMap = new HashMap<>();
//
//        // 드론의 모든 부품에 대하여 검사
//        for (Part part : drone.getParts()) {
//            // 각 부품의 카테고리를 기반으로 임계값 미만 점수를 매핑
//            if (part.getMotorScore() != null && part.getMotorScore() <= 70) {
//                abnormalPartsMap.computeIfAbsent("Motor", k -> new ArrayList<>())
//                        .add(PartScoreDto.of(part.getName(), part.getMotorScore(),null,null));
//            }
//            if (part.getMotorScore() != null && part.getBladeScore() <= 70) {
//                abnormalPartsMap.computeIfAbsent("Blade", k -> new ArrayList<>())
//                        .add(PartScoreDto.of(part.getName(), part.getBladeScore(),null,null));
//            }
//            if (part.getMotorScore() != null && part.getEscScore() <= 70) {
//                abnormalPartsMap.computeIfAbsent("Esc", k -> new ArrayList<>())
//                        .add(PartScoreDto.of(part.getName(), part.getEscScore(),null,null));
//            }
//
//        }
//
//        // abnormalPartsMap을 이용하여 AbnormalPartsDto 리스트 생성
//        List<AbnormalPartsDto> abnormalPartsDtos = abnormalPartsMap.entrySet().stream()
//                .map(entry -> AbnormalPartsDto.of(entry.getKey(), entry.getValue()))
//                .collect(Collectors.toList());
//
//        // OrderHistory와 매핑된 Product로부터 OrderPartsDto 리스트 생성
//        List<OrderHistory> orderHistories = orderHistoryRepository.findByDrone(drone);
//
//
//        List<OrderPartsDto> orderPartsDto = orderHistories.stream()
//                .map(orderHistory -> {
//                    Product product = orderHistory.getProduct();
//                    ProductInfoDto productInfoDto = ProductInfoDto.of(product, orderHistory);
//                    return OrderPartsDto.of(orderHistory, Collections.singletonList(productInfoDto), abnormalPartsDtos);
//
////                    return new OrderPartsDto(
////                            drone.getId(),
////                            drone.getDroneImage(),
////                            drone.getNickname(),
////                            drone.getCheckHistory().getBalanceScore(),
////                            drone.getCheckHistory().getTotalScore(),
////                            orderHistory.getOrderDate(),
////                            orderHistory.getOrderDate().plusDays(3),
////                            Collections.singletonList(productInfoDto),
////                            abnormalPartsDtos
////                    );
//                })
//                .collect(Collectors.toList());
//
//        return OrderPartsDetailResponse.of(orderPartsDto);
//    }
//
//
//
//}
