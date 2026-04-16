package re.sission6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import re.sission6.entity.Vehicles;
import re.sission6.respository.VehicleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    public Page<Vehicles> getPagedVehicles(int page, int size, String sortBy, String direction){
       Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Vehicles> coursePage = vehicleRepository.findAll(pageable);
        return coursePage;
    }
}
