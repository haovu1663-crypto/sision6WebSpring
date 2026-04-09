package re.sission6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.sission6.entity.PackingTicketsDto;
import re.sission6.entity.ParkingTickets;
import re.sission6.service.PackingTicketsService;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class ParkingTiketController {
    private final PackingTicketsService packingTicketsService;

    @GetMapping
    public ResponseEntity<?> getTicketsDto() {
        List<PackingTicketsDto> p = packingTicketsService.getPakingTicketsDto();
        if (p.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveTickets(@RequestBody ParkingTickets parkingTickets) {
        return new ResponseEntity<>(packingTicketsService.addPakingTickets(parkingTickets), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTickets(@RequestBody ParkingTickets parkingTickets,
                                           @PathVariable int id) {
        ParkingTickets p = packingTicketsService.getPakingTicketsbyid(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        // ✅ truyền id vào service để load đúng entity
        return new ResponseEntity<>(packingTicketsService.updatePakingTickets(id, parkingTickets), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTickets(@PathVariable int id) {
        ParkingTickets p = packingTicketsService.getPakingTicketsbyid(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        packingTicketsService.deletePakingTickets(id);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
}