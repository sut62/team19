package com.cpe.backend.RentCar.Controller;
import com.cpe.backend.RentCar.Entity.RentType;
import com.cpe.backend.RentCar.Repository.RentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RentTypeController {
    @Autowired
    private final RentTypeRepository rentTypeRepository;
    public RentTypeController(RentTypeRepository rentTypeRepository) {
        this.rentTypeRepository = rentTypeRepository;
    }
    @GetMapping("/rentType")
    public Collection<RentType> RentTypes() {
        return rentTypeRepository.findAll().stream().collect(Collectors.toList());
          }
}
