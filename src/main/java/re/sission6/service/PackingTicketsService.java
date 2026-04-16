package re.sission6.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.sission6.entity.*;
import re.sission6.respository.IPakingTickets;
import re.sission6.respository.VehicleRepository;
import re.sission6.respository.ZoneRepository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PackingTicketsService {
    private final IPakingTickets pakingTickets;
    private final VehicleRepository vehicleRepository;  //
    private final ZoneRepository zoneRepository;        //
  
    public List<PackingTicketsDto> getPakingTicketsDto() {
        return pakingTickets.getPakingTicketsDto();
    }

    public ParkingTickets addPakingTickets(ParkingTickets parkingTickets) {
        // ✅ Load đúng entity từ DB trước khi save
        Vehicles vehicle = vehicleRepository.findById(parkingTickets.getVehicle().getId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        Zones zone = zoneRepository.findById(parkingTickets.getZone().getId())
                .orElseThrow(() -> new RuntimeException("Zone not found"));
        parkingTickets.setVehicle(vehicle);
        parkingTickets.setZone(zone);
        return pakingTickets.save(parkingTickets);
    }

    public ParkingTickets updatePakingTickets(int id, ParkingTickets parkingTickets) {
        // ✅ Load đúng entity từ DB trước khi update
        ParkingTickets existing = pakingTickets.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        Vehicles vehicle = vehicleRepository.findById(parkingTickets.getVehicle().getId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        Zones zone = zoneRepository.findById(parkingTickets.getZone().getId())
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        existing.setCheckIn(parkingTickets.getCheckIn());
        existing.setCheckOut(parkingTickets.getCheckOut());
        existing.setVehicle(vehicle);
        existing.setZone(zone);
        return pakingTickets.save(existing);
    }

    public void deletePakingTickets(int id) {
        pakingTickets.deleteById(id);
    }

    public ParkingTickets getPakingTicketsbyid(int id) {
        return pakingTickets.findById(id).orElse(null);
    }


  

    @Transactional
    public TicketResponse checkIn(TicketRequest req) {
        // 1. Kiểm tra tồn tại
        Vehicles vehicle = vehicleRepository.findById(req.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy xe"));
        Zones zone = zoneRepository.findById(req.getZoneId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khu vực"));

        // 2. Kiểm tra trống chỗ
        if (zone.getOccupiedSpots() >= zone.getCapacity()) {
            throw new RuntimeException("Khu vực đã hết chỗ");
        }

        // 3. Tạo vé mới
        ParkingTickets ticket = new ParkingTickets();
        ticket.setVehicle(vehicle);
        ticket.setZone(zone);
        ticket.setCheckIn(LocalDateTime.now());

        // 4. Cập nhật số chỗ trong Zone
        zone.setOccupiedSpots(zone.getOccupiedSpots() + 1);
        zoneRepository.save(zone);

        ParkingTickets savedTicket = pakingTickets.save(ticket);

        return TicketResponse.builder()
                .id(savedTicket.getId())
                .licensePlate(vehicle.getLicensePlate())
                .zoneName(zone.getName())
                .checkInTime(savedTicket.getCheckIn())
                .build();
    }

    @Transactional
    public TicketResponse checkOut(Integer vehicleId) {
        // 1. Tìm vé chưa checkout
        ParkingTickets ticket = pakingTickets.findFirstByVehicleIdAndCheckOutIsNullOrderByCheckInDesc(vehicleId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vé đang sử dụng cho xe này"));

        // 2. Cập nhật thời gian ra
        ticket.setCheckOut(LocalDateTime.now());

        // 3. Giảm số chỗ trong Zone
        Zones zone = ticket.getZone();
        zone.setOccupiedSpots(Math.max(0, zone.getOccupiedSpots() - 1));
        zoneRepository.save(zone);

        pakingTickets.save(ticket);

        return TicketResponse.builder()
                .id(ticket.getId())
                .licensePlate(ticket.getVehicle().getLicensePlate())
                .zoneName(zone.getName())
                .checkInTime(ticket.getCheckIn())
                .checkOutTime(ticket.getCheckOut())
                .build();
    }

    public ApiResponse<List<TicketSummaryResponse>> getDailyTicketSummary() {
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);

        List<TicketSummaryResponse> tickets = pakingTickets.findDailyTickets(startOfDay, endOfDay);

        return new ApiResponse<>("Lấy danh sách vé trong ngày thành công", tickets);
    }
}