package com.cpe.backend;


import com.cpe.backend.Customer.Entity.Job;
import com.cpe.backend.Customer.Repository.JobRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class JobTest {

    private Validator validator;

    @Autowired
    private JobRepository jobRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6015107_testJobBeOK() {
        Job job = new Job();
        job.setJob("รับราชการ");

        job = jobRepository.saveAndFlush(job);

        Optional<Job> found = jobRepository.findById(job.getId());
        assertEquals("รับราชการ", found.get().getJob());

    }
    @Test
    void B6015107_testJobMustNotBeNull() {
        Job job = new Job();
        job.setJob(null);

        Set<ConstraintViolation<Job>> result = validator.validate(job);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Job> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("job", v.getPropertyPath().toString());
    }
}