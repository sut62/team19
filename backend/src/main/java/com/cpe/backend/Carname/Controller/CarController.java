package com.cpe.backend.Carname.Controller;

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

import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.Carname.Entity.Province;
import com.cpe.backend.Carname.Entity.Brand;
import com.cpe.backend.Carname.Entity.Carseat;
import com.cpe.backend.Carname.Entity.Car;

import com.cpe.backend.FileSharing.Repository.EmployeeRepository;
import com.cpe.backend.Carname.Repository.BrandRepository;
import com.cpe.backend.Carname.Repository.CarRepository;
import com.cpe.backend.Carname.Repository.CarseatRepository;
import com.cpe.backend.Carname.Repository.ProvinceRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CarController {
    @Autowired
    private final CarRepository carRepository;
    @Autowired
    private CarseatRepository carseatRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/car")
    public Collection<Car> Cars() {
        return carRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/car/{plate}")
    public Collection<Car> getCarByPlate(@PathVariable("plate") String plate) {
        return carRepository.findCarByPlate(plate);
    }

    @PostMapping("/car/{vins}/{plates}/{seat_id}/{brand_id}/{province_id}/{employee_id}")
    public Car newCar(Car newCar,
    @PathVariable String plates,
    @PathVariable String vins,
    @PathVariable long seat_id,
    @PathVariable long brand_id,
    @PathVariable long province_id,
    @PathVariable long employee_id) 
    {


    Carseat carseat = carseatRepository.findById(seat_id);
    Brand brandd = brandRepository.findById(brand_id);
    Province province = provinceRepository.findById(province_id);
    Employee employee = employeeRepository.findById(employee_id);

    newCar.setPlate(plates);
    newCar.setVin(vins);
    newCar.setCarseat(carseat);
    newCar.setCarbrand(brandd);
    newCar.setPlateprovince(province);
    newCar.setCreatedby(employee);

    return carRepository.save(newCar); 
    
    }
}