package re.sission6.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.sission6.entity.Zones;

public interface ZoneRepository extends JpaRepository<Zones, Integer> {
}
