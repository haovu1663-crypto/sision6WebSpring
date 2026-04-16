package re.sission6.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import re.sission6.entity.PackingTicketsDto;
import re.sission6.entity.ParkingTickets;
import re.sission6.entity.TicketSummaryResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IPakingTickets extends JpaRepository<ParkingTickets, Integer> {
    @Query("select new re.sission6.entity.PackingTicketsDto(p.id, p.checkIn, p.checkOut, p.vehicle.id, p.zone.id) from ParkingTickets p")
    List<PackingTicketsDto> getPakingTicketsDto();
    // Tìm vé mới nhất của xe mà chưa check-out
    Optional<ParkingTickets> findFirstByVehicleIdAndCheckOutIsNullOrderByCheckInDesc(Integer vehicleId);
    @Query("SELECT new re.sission6.entity.TicketSummaryResponse(" +
            "t.id, t.vehicle.licensePlate, t.zone.name, t.checkIn, t.checkOut) " +
            "FROM ParkingTickets t " +
            "WHERE t.checkIn >= :startOfDay AND t.checkIn <= :endOfDay")
    List<TicketSummaryResponse> findDailyTickets(
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay);
}
