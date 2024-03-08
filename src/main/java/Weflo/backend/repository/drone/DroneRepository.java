package Weflo.backend.repository.drone;

import Weflo.backend.domain.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findAllByUserId(Long userId);
    Optional<Drone> findByIdAndUserId(Long droneId, Long userId);


}
