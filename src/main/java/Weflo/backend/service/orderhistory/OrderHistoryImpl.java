package Weflo.backend.service.orderhistory;

import Weflo.backend.domain.Drone;
import Weflo.backend.domain.OrderHistory;
import Weflo.backend.domain.Product;
import Weflo.backend.dto.common.OrderHistoryDto;
import Weflo.backend.dto.common.OrderStatusDto;
import Weflo.backend.dto.orderhistory.response.AllOrderHistoriesResponse;
import Weflo.backend.repository.drone.DroneRepository;
import Weflo.backend.repository.orderhistory.OrderHistoryRepository;
import Weflo.backend.repository.product.ProductRepository;
import com.amazonaws.services.kms.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderHistoryImpl implements OrderHistoryService {
    private final OrderHistoryRepository orderHistoryRepository;
    private final DroneRepository droneRepository;
    private final ProductRepository productRepository;

    @Override
    public AllOrderHistoriesResponse getOrderHistories(Long userId, Integer month, String orderStatus) {
        List<Drone> drones = findDronesByUserId(userId);
        if (drones.isEmpty()) throw new NotFoundException("User has no drone");

        return processOrderHistories(drones, month, orderStatus);
    }

    private List<Drone> findDronesByUserId(Long userId) {
        return droneRepository.findAllByUserId(userId);
    }

    private AllOrderHistoriesResponse processOrderHistories(List<Drone> drones, Integer month, String orderStatus) {
        List<OrderHistoryDto> orderHistories = new ArrayList<>();
        Map<String, Integer> statusMap = new HashMap<>();
        statusMap.put("배송준비중", 0); statusMap.put("배송중", 0);
        statusMap.put("배송완료", 0); statusMap.put("구매확정", 0);
        int sumPrice = 0;

        for (Drone drone : drones) {
            List<OrderHistory> orders;

            if (month != null) {
                // 월의 시작일과 종료일을 계산
                LocalDate startOfMonth = LocalDate.of(LocalDate.now().getYear(), month, 1);
                LocalDate endOfMonth = startOfMonth.plusMonths(1).minusDays(1);
                if (orderStatus == null || !orderStatus.isBlank()) {
                    // 특정 달 + 배송 상태의 주문 배송 내역 반환
                    orders = orderHistoryRepository.findAllByDroneIdAndOrderDateBetweenAndOrderHistoryStatus(drone.getId(), startOfMonth, endOfMonth, orderStatus);
                } else {
                    // 특정 달의 주문 배송 내역 반환
                    orders = orderHistoryRepository.findAllByDroneIdAndOrderDateBetween(drone.getId(), startOfMonth, endOfMonth);
                }
            } else if (orderStatus == null || !orderStatus.isBlank()) {
                // 특정 상태의 주문 배송 내역 반환
                orders = orderHistoryRepository.findAllByDroneIdAndOrderHistoryStatus(drone.getId(), orderStatus);
            } else {
                // 유저의 모든 주문 배송 내역 반환
                orders = orderHistoryRepository.findAllByDroneId(drone.getId());
            }

            for (OrderHistory order : orders) {
                Product product = productRepository.findById(order.getProduct().getId())
                        .orElseThrow(() -> new NotFoundException("Product not found"));

                OrderHistoryDto orderHistoryDto = OrderHistoryDto.builder()
                        .id(order.getId())
                        .category(product.getCategory())
                        .name(product.getName())
                        .salePrice(product.getSalePrice())
                        .amount(order.getAmount())
                        .orderDate(order.getOrderDate())
                        .status(order.getOrderHistoryStatus())
                        .productImage(product.getProductImage())
                        .build();
                orderHistories.add(orderHistoryDto);

                sumPrice += product.getSalePrice() * order.getAmount();

                // orderHistoryStatus가 {배송준비중, 배송중, 배송완료, 구매확정} 에 속하는 것들만 put
                String orderHistoryStatus = order.getOrderHistoryStatus();
                if (statusMap.containsKey(orderHistoryStatus)) {
                    statusMap.put(orderHistoryStatus, statusMap.get(orderHistoryStatus) + 1);
                }
            }
        }

        List<OrderStatusDto> orderStatuses = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : statusMap.entrySet()) {
            OrderStatusDto orderStatusDto = OrderStatusDto.builder()
                    .statusName(entry.getKey())
                    .amount(entry.getValue())
                    .build();
            orderStatuses.add(orderStatusDto);
        }

        return AllOrderHistoriesResponse.builder()
                .sumPrice(sumPrice)
                .orderStatuses(orderStatuses)
                .orderHistories(orderHistories)
                .build();
    }
}