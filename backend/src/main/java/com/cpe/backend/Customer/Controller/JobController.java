package com.cpe.backend.Customer.Controller;

import com.cpe.backend.Customer.Entity.Job;
import com.cpe.backend.Customer.Repository.JobRepository;
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
public class JobController {

    @Autowired
    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping("/job")
    public Collection<Job> Job() {
        return jobRepository.findAll().stream().collect(Collectors.toList());
    }

}