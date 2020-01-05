package com.cpe.backend.Carreturn.Controller;
import com.cpe.backend.Carreturn.Entity.Payforfine;
import com.cpe.backend.Carreturn.Repository.PayforfineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class PayforfineController {

    @Autowired
    private final PayforfineRepository payforfineRepository;

    public PayforfineController(PayforfineRepository payforfineRepository) {
        this.payforfineRepository = payforfineRepository;
    }

    @GetMapping("/payforfine")
    public Collection<Payforfine> Payforfinees() {
        return payforfineRepository.findAll().stream().collect(Collectors.toList());
    }

    

}