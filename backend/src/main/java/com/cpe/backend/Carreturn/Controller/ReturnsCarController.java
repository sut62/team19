package com.cpe.backend.Carreturn.Controller;
import com.cpe.backend.Customer.Entity.Customer;
import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.RentCar.Entity.RentCar;
import com.cpe.backend.Carreturn.Entity.ReturnsCar;
import com.cpe.backend.Carreturn.Entity.Payforfine;

import com.cpe.backend.Customer.Repository.CustomerRepository;
import com.cpe.backend.FileSharing.Repository.EmployeeRepository;
import com.cpe.backend.RentCar.Repository.RentCarRepository;
import com.cpe.backend.Carreturn.Repository.ReturnsCarRepository;
import com.cpe.backend.Carreturn.Repository.PayforfineRepository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import java.time.LocalDateTime;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ReturnsCarController {
    @Autowired
    private final ReturnsCarRepository returnsCarRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RentCarRepository rentCarRepository;
    @Autowired
    private PayforfineRepository payforfineRepository;

    ReturnsCarController(ReturnsCarRepository returnsCarRepository) {
        this.returnsCarRepository = returnsCarRepository;
    }

    @GetMapping("/returnsCar")
    public Collection<ReturnsCar> ReturnsCars() {
        return returnsCarRepository.findAll().stream().collect(Collectors.toList());
    }

     
    @GetMapping("/returnsCar/{rentid}")
    public Collection<ReturnsCar> getCustomerByName(@PathVariable("rentid") String rentid ){
         return returnsCarRepository.findByRentcar(rentid);
     }


    @PostMapping("/returnsCar/{note}/{employee_id}/{rentcar_id}/{payforfine_id}")
    public ReturnsCar newReturnsCar(ReturnsCar newReturnsCar,
    @PathVariable long employee_id,
    @PathVariable long rentcar_id,
    @PathVariable long payforfine_id,
    @PathVariable String note) {
   

   
    RentCar rents = rentCarRepository.findById(rentcar_id);
    Employee createdby = employeeRepository.findById(employee_id);
    Payforfine pays = payforfineRepository.findById(payforfine_id);
    LocalDateTime returndate = LocalDateTime.now();

    newReturnsCar.setRents(rents);
    newReturnsCar.setCreatedby(createdby);
    newReturnsCar.setPays(pays);
    newReturnsCar.setNote(note);
    newReturnsCar.setReturndate(returndate);


    return returnsCarRepository.save(newReturnsCar); 
    
    }
}