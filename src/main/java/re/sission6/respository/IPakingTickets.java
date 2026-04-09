package re.sission6.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import re.sission6.entity.PackingTicketsDto;
import re.sission6.entity.ParkingTickets;

import java.util.List;

@Repository
public interface IPakingTickets extends JpaRepository<ParkingTickets, Integer> {
    @Query("select new re.sission6.entity.PackingTicketsDto(p.id, p.checkIn, p.checkOut, p.vehicle.id, p.zone.id) from ParkingTickets p")
    List<PackingTicketsDto> getPakingTicketsDto();
}
