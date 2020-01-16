package com.cpe.backend;

import com.cpe.backend.RentCar.Entity.RentCar;
import com.cpe.backend.RentCar.Repository.RentCarRepository;
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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class RentCarTest {

    private Validator validator;

    @Autowired
    private RentCarRepository rentcarRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    LocalDateTime rentDate = LocalDateTime.now();
    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void b6004804_test_InsertDataNormal() {
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(rentDate);
        rentcar.setRentday(4);
        rentcar.setPrice(12000);

        rentcar = rentcarRepository.saveAndFlush(rentcar);

        Optional<RentCar> found = rentcarRepository.findById(rentcar.getId());
        assertEquals(4, found.get().getRentday());
    }

    @Test
    void b6004804_test_MustNotBeNull() {
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(rentDate);
        rentcar.setRentday(null);
        rentcar.setPrice(12000);

        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("rentday", v.getPropertyPath().toString());
    }

    @Test
    void b6004804_test_MustBePositiveOrZero() {
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(rentDate);
        rentcar.setRentday(-1);
        rentcar.setPrice(12000);

        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must be greater than or equal to 0", v.getMessage());
        assertEquals("rentday", v.getPropertyPath().toString());
    }

    @Test
    void b6004804_test_MustBeMax() {
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(LocalDateTime.now());
        rentcar.setRentday(12345678);
        rentcar.setPrice(12000);

        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must be less than or equal to 7", v.getMessage());
        assertEquals("rentday", v.getPropertyPath().toString());
    }

}








