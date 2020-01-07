package com.cpe.backend.Maintenance.Controller;

import com.cpe.backend.Maintenance.Entity.Service;
import com.cpe.backend.Maintenance.Repository.ServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection; 
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ServiceController {

    @Autowired
    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/service")
    public Collection<Service> Services() {
        return serviceRepository.findAll().stream().collect(Collectors.toList());
    }

    
}