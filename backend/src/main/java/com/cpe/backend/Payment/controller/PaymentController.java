package com.cpe.backend.Payment.controller;

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
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.cpe.backend.Payment.entity.Payment;
import com.cpe.backend.Payment.entity.PaymentOptions;
import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.RentCar.Entity.RentCar;
import com.cpe.backend.Customer.Entity.Customer;

import com.cpe.backend.Payment.repository.PaymentRepository;
import com.cpe.backend.Payment.repository.OptionsRepository;
import com.cpe.backend.FileSharing.Repository.EmployeeRepository;
import com.cpe.backend.RentCar.Repository.RentCarRepository;
import com.cpe.backend.Customer.Repository.CustomerRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PaymentController {
    @Autowired
    private final PaymentRepository paymentRepository;
    @Autowired
    private OptionsRepository optionsRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RentCarRepository rentcarRepository;
    @Autowired
    private CustomerRepository customerRepository;

    PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository= paymentRepository;
    }

    @GetMapping("/payment")
    public Collection<Payment> Payment() {
        return paymentRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/payment/{rentid}")
    public Collection<Payment> findByNameCustomer(@PathVariable("rentid") String rentid ){
         return paymentRepository.findByRentcar(rentid);
    }

    @PostMapping("/payment/{employee_id}/{rentcar_id}/{options_id}/{note}")
    public Payment newPayment(Payment newPayment,
                                      @PathVariable long options_id,
                                      @PathVariable long employee_id,
                                      @PathVariable long rentcar_id,
                                      @PathVariable String note) {
        

        Employee createdby = employeeRepository.findById(employee_id);
        RentCar rent = rentcarRepository.findById(rentcar_id);
        PaymentOptions payoptions = optionsRepository.findById(options_id);
        LocalDateTime date = LocalDateTime.now();


        newPayment.setCreatedby(createdby);
        newPayment.setRent(rent);
        newPayment.setPayoptions(payoptions);
        newPayment.setDate(date);
        newPayment.setNote(note);

        return paymentRepository.save(newPayment); 
    }
}
