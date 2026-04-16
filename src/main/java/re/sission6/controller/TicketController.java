package re.sission6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.sission6.entity.TicketRequest;
import re.sission6.entity.TicketResponse;
import re.sission6.service.PackingTicketsService;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final PackingTicketsService packingTicketsService;
    @PostMapping("/check-in")
    public ResponseEntity<TicketResponse> checkIn(@RequestBody TicketRequest request) {
        return ResponseEntity.ok(packingTicketsService.checkIn(request));
    }

    @PutMapping("/check-out/{vehicleId}")
    public ResponseEntity<TicketResponse> checkOut(@PathVariable Integer vehicleId) {
        return ResponseEntity.ok(packingTicketsService.checkOut(vehicleId));
    }
}
