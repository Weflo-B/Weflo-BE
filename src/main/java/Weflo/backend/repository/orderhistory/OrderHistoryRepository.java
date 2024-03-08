package Weflo.backend.repository.orderHistory;


import Weflo.backend.domain.Drone;
import Weflo.backend.domain.OrderHistory;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
    List<OrderHistory> findByDrone(Drone droneId);
}
