package com.cpe.backend;

import com.cpe.backend.Maintenance.Entity.Service;

import com.cpe.backend.Maintenance.Repository.ServiceRepository;

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
public class ServiceTest {

    private Validator validator;

    @Autowired
    private ServiceRepository serviceRepository;

    
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void test_InsertDataNormal() {
        Service service = new Service();
        service.setService("เกี่ยวกับช่วงล่าง/เบรก/ล้อ");

        service = serviceRepository.saveAndFlush(service);

        Optional<Service> found = serviceRepository.findById(service.getId());
        assertEquals("เกี่ยวกับช่วงล่าง/เบรก/ล้อ", found.get().getService());
    }

    @Test
    void b6012328_testServiceMustNotBeNull() {
        Service service = new Service();
        service.setService(null);

        Set<ConstraintViolation<Service>> result = validator.validate(service);
        assertEquals(1, result.size());

        ConstraintViolation<Service> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("service", v.getPropertyPath().toString());
    }


}
