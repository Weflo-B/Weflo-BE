package Weflo.backend.repository.orderpart;

import Weflo.backend.domain.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderPartRepository extends JpaRepository<OrderHistory, Long> {
    Optional<OrderHistory> findById(Long droneId);
}
