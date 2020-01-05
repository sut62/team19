package com.cpe.backend.Payment.Controller;

import com.cpe.backend.Payment.Entity.PaymentOptions;
import com.cpe.backend.Payment.Repository.OptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class OptionsController {

    @Autowired
    private final OptionsRepository optionsRepository;

    public OptionsController(OptionsRepository optionsRepository) {
        this.optionsRepository = optionsRepository;
    }

    @GetMapping("/options")
    public Collection<PaymentOptions> PaymentOptions() {
        return optionsRepository.findAll().stream().collect(Collectors.toList());
    }

}
