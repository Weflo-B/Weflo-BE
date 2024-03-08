package Weflo.backend.repository.deliverydetail;

import Weflo.backend.domain.DeliveryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDetailRepository extends JpaRepository<DeliveryDetail, Long> {
    DeliveryDetail findByOrderHistoryId(Long orderHistoryId);
}
