package com.cpe.backend.Carname.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.cpe.backend.Carname.Entity.Carseat;
import com.cpe.backend.Carname.Repository.CarseatRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CarseatController {

    @Autowired
    private final CarseatRepository carseatRepository;

    public CarseatController(CarseatRepository carseatRepository) {
        this.carseatRepository = carseatRepository;
    }

    @GetMapping("/carseat")
    public Collection<Carseat> Carseats() {
        return carseatRepository.findAll().stream().collect(Collectors.toList());
    }

}