package com.cpe.backend.Customer.Controller;
import com.cpe.backend.Customer.Entity.Customer;
import com.cpe.backend.Customer.Entity.RegisType;
import com.cpe.backend.Customer.Entity.Job;
import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.Customer.Repository.CustomerRepository;
import com.cpe.backend.Customer.Repository.RegisTypeRepository;
import com.cpe.backend.Customer.Repository.JobRepository;
import com.cpe.backend.FileSharing.Repository.EmployeeRepository;

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
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;




import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CustomerController {
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private RegisTypeRepository regisTypeRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customer")
    public Collection<Customer> Customer() {
        return customerRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/customer/{name}")
    public Collection<Customer> getCustomerByName(@PathVariable("name") String name) {
        return customerRepository.findCustomerByName(name);
    }

    @PostMapping("/customer/{name}/{card_num}/{job_id}/{age}/{address}/{tel}/{registype_id}/{employee_id}")
    public Customer newCustomer(Customer newCustomer,
    @PathVariable String name,
    @PathVariable String card_num,
    @PathVariable long registype_id,
    @PathVariable int age,
    @PathVariable String address,
    @PathVariable long job_id,
    @PathVariable String tel,
    @PathVariable long employee_id
    ) {

    Job  work= jobRepository.findById(job_id);
    Employee createdBy = employeeRepository.findById(employee_id);
    RegisType type = regisTypeRepository.findById(registype_id);
    LocalDateTime date = LocalDateTime.now();

    newCustomer.setWork(work);
    newCustomer.setName(name);
    newCustomer.setCard_num(card_num);
    newCustomer.setAge(age);
    newCustomer.setAddress(address);
    newCustomer.setTel(tel);
    newCustomer.setCreatedBy(createdBy);
    newCustomer.setType(type);
    newCustomer.setDate(date);

    return customerRepository.save(newCustomer);
    
    }
}