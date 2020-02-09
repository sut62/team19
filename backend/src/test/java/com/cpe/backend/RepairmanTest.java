package com.cpe.backend;

import com.cpe.backend.Maintenance.Entity.Repairman;

import com.cpe.backend.Maintenance.Repository.RepairmanRepository;

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
public class RepairmanTest {

    private Validator validator;

    @Autowired
    private RepairmanRepository repairmanRepository;

    
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6012328_testInsertDataNormal() {
        Repairman repairman = new Repairman();
        repairman.setRepairman("สมศักดิ์ จิตรดี");
        repairman.setUsername("somsak");
        repairman.setPassword("ss12345678");

        repairman = repairmanRepository.saveAndFlush(repairman);

        Optional<Repairman> found = repairmanRepository.findById(repairman.getId());
        assertEquals("สมศักดิ์ จิตรดี", found.get().getRepairman());
    }

    @Test
    void b6012328_testRepairmanMustNotBeNull() {
        Repairman repairman = new Repairman();
        repairman.setRepairman(null);
        repairman.setUsername("somsak");
        repairman.setPassword("ss12345678");

        Set<ConstraintViolation<Repairman>> result = validator.validate(repairman);
        assertEquals(1, result.size());

        ConstraintViolation<Repairman> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("repairman", v.getPropertyPath().toString());
    }

    @Test
    void b6012328_testUsernameMustNotBeNull() {
        Repairman repairman = new Repairman();
        repairman.setRepairman("สมศักดิ์ จิตรดี");
        repairman.setUsername(null);
        repairman.setPassword("ss12345678");

        Set<ConstraintViolation<Repairman>> result = validator.validate(repairman);
        assertEquals(1, result.size());

        ConstraintViolation<Repairman> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("username", v.getPropertyPath().toString());
    }

    @Test
    void b6012328_testPasswordMustNotBeNull() {
        Repairman repairman = new Repairman();

        repairman.setRepairman("สมศักดิ์ จิตรดี");
        repairman.setUsername("somsak");
        repairman.setPassword(null);

        Set<ConstraintViolation<Repairman>> result = validator.validate(repairman);
        assertEquals(1, result.size());

        ConstraintViolation<Repairman> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("password", v.getPropertyPath().toString());
    }

   
    

}








