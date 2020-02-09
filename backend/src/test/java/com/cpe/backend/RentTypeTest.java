package com.cpe.backend;

import com.cpe.backend.RentCar.Entity.RentType;

import com.cpe.backend.RentCar.Repository.RentTypeRepository;

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
public class RentTypeTest {

    private Validator validator;

    @Autowired
    private RentTypeRepository rentTypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    
    @Test
    void b6004804_test_InsertDataNormal() {
        RentType rentType = new RentType();
        rentType.setUnitprice(600);
        rentType.setNametype("รายวัน");

        rentType = rentTypeRepository.saveAndFlush(rentType);

        Optional<RentType> found = rentTypeRepository.findById(rentType.getId());
        assertEquals(600, found.get().getUnitprice());
    }

    @Test
    void b6004804_test_UnitpriceMustNotBeNull() {
        System.out.println("\n=======================================");
        System.out.println("\nTest unitprice Must Not Be Null");
        System.out.println("\n=======================================\n");
        RentType rentType = new RentType();
        rentType.setUnitprice(null);
        rentType.setNametype("รายวัน");

        Set<ConstraintViolation<RentType>> result = validator.validate(rentType);
        assertEquals(1, result.size());

        ConstraintViolation<RentType> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("unitprice", v.getPropertyPath().toString());
    }

    @Test
    void b6004804_test_NametypeMustNotBeNull() {
        System.out.println("\n=======================================");
        System.out.println("\nTest nametype Must Not Be Null");
        System.out.println("\n=======================================\n");
        RentType rentType = new RentType();
        rentType.setUnitprice(600);
        rentType.setNametype(null);

        Set<ConstraintViolation<RentType>> result = validator.validate(rentType);
        assertEquals(1, result.size());

        ConstraintViolation<RentType> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("nametype", v.getPropertyPath().toString());
    }
}








