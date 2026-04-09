package re.sission6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.sission6.entity.PackingTicketsDto;
import re.sission6.entity.ParkingTickets;
import re.sission6.entity.Vehicles;
import re.sission6.entity.Zones;
import re.sission6.respository.IPakingTickets;
import re.sission6.respository.VehicleRepository;
import re.sission6.respository.ZoneRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PackingTicketsService {
    private final IPakingTickets pakingTickets;
    private final VehicleRepository vehicleRepository;  // ✅ inject thêm
    private final ZoneRepository zoneRepository;        // ✅ inject thêm

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
}