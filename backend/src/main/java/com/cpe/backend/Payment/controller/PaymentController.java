package com.cpe.backend.Payment.Controller;

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

import com.cpe.backend.Payment.Entity.Payment;
import com.cpe.backend.Payment.Entity.PaymentOptions;
import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.RentCar.Entity.RentCar;

import com.cpe.backend.Payment.Repository.PaymentRepository;
import com.cpe.backend.Payment.Repository.OptionsRepository;
import com.cpe.backend.FileSharing.Repository.EmployeeRepository;
import com.cpe.backend.RentCar.Repository.RentCarRepository;


@CrossOrigin(origins = "http://localhost:8081")
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

    PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository= paymentRepository;
    }

    @GetMapping("/payment")
    public Collection<Payment> Payment() {
        return paymentRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/payment/{employee_id}/{rentcar_id}/{options_id}")
    public Payment newPayment(Payment newPayment,
                                      @PathVariable long options_id,
                                      @PathVariable long employee_id,
                                      @PathVariable long rentcar_id) {
        
        PaymentOptions payoptions = optionsRepository.findById(options_id);
        Employee createdby = employeeRepository.findById(employee_id);
        RentCar rent = rentcarRepository.findById(rentcar_id);
        LocalDateTime date = LocalDateTime.now();

        newPayment.setPayoptions(payoptions);
        newPayment.setCreatedby(createdby);
        newPayment.setRent(rent);
        newPayment.setDate(date);

        return paymentRepository.save(newPayment); 
    }
}
