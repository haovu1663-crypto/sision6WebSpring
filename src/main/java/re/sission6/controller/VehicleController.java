package re.sission6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import re.sission6.common.ApiResponse;
import re.sission6.entity.Vehicles;
import re.sission6.service.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;
    @GetMapping
    public ResponseEntity<?> getAllVehicles(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam String direction) {
      Page<Vehicles> a = vehicleService.getPagedVehicles(page, size, sortBy, direction);
        ApiResponse b = new ApiResponse(a);
      return new ResponseEntity<>(b, HttpStatus.OK);
    }
}
