package Weflo.backend.repository.orderhistory;

import Weflo.backend.domain.Drone;
import Weflo.backend.domain.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
    List<OrderHistory> findByDrone(Drone droneId);
    List<OrderHistory> findAllByDroneId(Long droneId);
    List<OrderHistory> findAllByDroneIdAndOrderHistoryStatus(Long drone_id, String orderHistoryStatus);
    List<OrderHistory> findAllByDroneIdAndOrderDateBetween(Long droneId, LocalDate startOfMonth, LocalDate endOfMonth);
    List<OrderHistory> findAllByDroneIdAndOrderDateBetweenAndOrderHistoryStatus(Long droneId, LocalDate startOfMonth, LocalDate endOfMonth, String orderHistoryStatus);
}