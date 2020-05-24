package com.cpe.backend.FileSharing.Controller;
import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.FileSharing.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EmployeeController {
    @Autowired
    private final EmployeeRepository employeeRepository;
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @GetMapping("/employee/{username}/{password}")
    public Collection<Employee> getEmployeeByUsername(@PathVariable("username") String username,
    												@PathVariable("password") String password) {
        return employeeRepository.findEmployeeByUsername(username,password);
    }
    @GetMapping("/employee")
    public Collection<Employee> Employees() {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }
}
