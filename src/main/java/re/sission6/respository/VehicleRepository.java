package re.sission6.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.sission6.entity.Vehicles;

public interface VehicleRepository extends JpaRepository<Vehicles, Integer> {
}
