package com.cpe.backend.Customer.Controller;

import com.cpe.backend.Customer.Entity.RegisType;
import com.cpe.backend.Customer.Repository.RegisTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class RegisTypeController {
    @Autowired
    private final RegisTypeRepository regisTypeRepository;
    public RegisTypeController(RegisTypeRepository regisTypeRepository) {
        this.regisTypeRepository = regisTypeRepository;
    }

    @GetMapping("/regisType")
    public Collection<RegisType> RegisType() {
        return regisTypeRepository.findAll().stream().collect(Collectors.toList());
    }

}