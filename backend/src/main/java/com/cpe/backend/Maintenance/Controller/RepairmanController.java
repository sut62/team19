package com.cpe.backend.Maintenance.Controller;

import com.cpe.backend.Maintenance.Entity.Repairman;
import com.cpe.backend.Maintenance.Repository.RepairmanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class RepairmanController {
    @Autowired
    private final RepairmanRepository repairmanRepository;

    public RepairmanController(RepairmanRepository repairmanRepository) {
        this.repairmanRepository = repairmanRepository;
    }

    @GetMapping("/repairman")
    public Collection<Repairman> Repairmans() {
        return repairmanRepository.findAll().stream().collect(Collectors.toList());
    }
     @GetMapping("/repairman/{username}/{password}")
    public Collection<Repairman> getRepairmanByUsername(@PathVariable("username") String username,
    												@PathVariable("password") String password) {
        return repairmanRepository.findRepairmanByUsername(username,password);

	}

}