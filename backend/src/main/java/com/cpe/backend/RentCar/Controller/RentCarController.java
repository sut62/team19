package com.cpe.backend.RentCar.Controller;
import com.cpe.backend.Customer.Entity.Customer;
import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.RentCar.Entity.RentCar;
import com.cpe.backend.Carname.Entity.Car;
import com.cpe.backend.RentCar.Entity.RentType;

import com.cpe.backend.RentCar.Repository.RentTypeRepository;
import com.cpe.backend.Customer.Repository.CustomerRepository;
import com.cpe.backend.FileSharing.Repository.EmployeeRepository;
import com.cpe.backend.RentCar.Repository.RentCarRepository;
import com.cpe.backend.Carname.Repository.CarRepository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.lang.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RentCarController {
    @Autowired
    private final RentCarRepository rentCarRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RentTypeRepository rentTypeRepository;
    RentCarController(RentCarRepository rentCarRepository) {
        this.rentCarRepository = rentCarRepository;
    }
    @GetMapping("/rentCar")
    public Collection<RentCar> RentCars() {
        return rentCarRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/rentCar/{id}")
    public Collection<RentCar> getIdRentCarCustomer(@PathVariable("id") Long id) {
        return rentCarRepository.findByIdCustomer(id);
    }

    @PostMapping("/rentCar/{rentdays}/{type_id}/{customer_id}/{car_id}/{employee_id}/{t_price}")
    public RentCar newRentCar(RentCar newRentCar,
                                    @PathVariable long customer_id,
                                    @PathVariable long car_id,
                                    @PathVariable long employee_id,
                                    @PathVariable Integer rentdays,
                                    @PathVariable long type_id,
                                    @PathVariable Integer t_price
                                    ) {

        Customer rentalBy = customerRepository.findById(customer_id);
        Car car = carRepository.findById(car_id);
        Employee createdby = employeeRepository.findById(employee_id);
        RentType types = rentTypeRepository.findById(type_id);
        Integer prices;
        prices = TotalPrice(rentdays,t_price);
        LocalDateTime rentDate = LocalDateTime.now();
        
        newRentCar.setRentalBy(rentalBy);
        newRentCar.setSelectcar(car);
        newRentCar.setRentDate(rentDate);
        newRentCar.setCreatedby(createdby);
        newRentCar.setRentday(rentdays);
        newRentCar.setType(types);
        newRentCar.setPrice(prices);
        return rentCarRepository.save(newRentCar);
    }
    public Integer TotalPrice(Integer day,Integer t_price){
        return day*t_price;
    }
}
