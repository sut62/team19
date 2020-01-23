package com.cpe.backend.Maintenance.Controller;

import com.cpe.backend.Maintenance.Entity.Maintenance;
import com.cpe.backend.Maintenance.Entity.Repairman;
import com.cpe.backend.Maintenance.Entity.Service;
import com.cpe.backend.Carname.Entity.Car;

import com.cpe.backend.Maintenance.Repository.MaintenanceRepository;
import com.cpe.backend.Maintenance.Repository.RepairmanRepository;
import com.cpe.backend.Maintenance.Repository.ServiceRepository;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class MaintenanceController {
    @Autowired
    private final MaintenanceRepository maintenanceRepository;
    @Autowired
    private RepairmanRepository repairmanRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired

    MaintenanceController(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @GetMapping("/maintenance")
    public Collection<Maintenance> Maintenances() {
        return maintenanceRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/maintenance/{plate}")
    public Collection<Maintenance> getMaintenanceByPlate(@PathVariable("plate") String plate) {
        return maintenanceRepository.findMaintenanceByPlate(plate);
    }

    @PostMapping("/maintenance/{messege_boxes}/{auto_parts}/{mileages}/{repairman_id}/{car_id}/{service_id}")
    public Maintenance newMaintenance(Maintenance newMaintenance,
            @PathVariable long repairman_id,
            @PathVariable long car_id,
            @PathVariable long service_id,
            @PathVariable String messege_boxes,
            @PathVariable String auto_parts,
            @PathVariable long mileages
            ){

    Repairman rm = repairmanRepository.findById(repairman_id);
    Car c = carRepository.findById(car_id);
    Service s = serviceRepository.findById(service_id);
    LocalDateTime fixdate = LocalDateTime.now();
   
    newMaintenance.setFixedBy(rm);
    newMaintenance.setCar(c);
    newMaintenance.setService(s);
    newMaintenance.setMessageBox(messege_boxes);
    newMaintenance.setAutoPart(auto_parts);
    newMaintenance.setFixdate(fixdate);
    newMaintenance.setMileage(mileages);


    return maintenanceRepository.save(newMaintenance);
    
    }
}