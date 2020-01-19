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
    //======================================================================
    //=                             [ Test rentday ]                       =
    //======================================================================
    @Test
    void b6004804_test_RentdayMustNotBeNull() {
        System.out.println("\n=======================================");
        System.out.println("\nTest rentday Must Not Be Null");
        System.out.println("\n=======================================\n");
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
    void b6004804_test_RentdayMustBePositiveOrZero() {
        System.out.println("\n==========================================");
        System.out.println("\nTest rentday Must Be Positive Or Zero");
        System.out.println("\n==========================================\n");
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
    void b6004804_test_RentdayMustBeMax() {
        System.out.println("\n=======================================");
        System.out.println("\nTest rentday Must Be Max");
        System.out.println("\n=======================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(LocalDateTime.now());
        rentcar.setRentday(367);
        rentcar.setPrice(12000);

        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must be less than or equal to 366", v.getMessage());
        assertEquals("rentday", v.getPropertyPath().toString());
    }

    //======================================================================
    //=                             [ Test price ]                       =
    //======================================================================
    @Test
    void b6004804_test_PriceMustNotBeNull() {
        System.out.println("\n=======================================");
        System.out.println("\nTest price Must Not Be Null");
        System.out.println("\n=======================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(rentDate);
        rentcar.setRentday(4);
        rentcar.setPrice(null);

        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());
    }

    @Test
    void b6004804_test_PriceMustBeMin() {
        System.out.println("\n==========================================");
        System.out.println("\nTest price Must Be Min");
        System.out.println("\n==========================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(rentDate);
        rentcar.setRentday(4);
        rentcar.setPrice(599);

        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must be greater than or equal to 600", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());
    }

    @Test
    void b6004804_test_PriceMustBeMax() {
        System.out.println("\n=======================================");
        System.out.println("\nTest price Must Be Max");
        System.out.println("\n=======================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(LocalDateTime.now());
        rentcar.setRentday(4);
        rentcar.setPrice(1234567890);

        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must be less than or equal to 999999999", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());
    }

    //======================================================================
    //=                             [ Test rentDate]                       =
    //======================================================================
    @Test
    void b6004804_test_rentDateMustNotBeNull() {
        System.out.println("\n=======================================");
        System.out.println("\nTest rentDate Must Not Be Null");
        System.out.println("\n=======================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(null);
        rentcar.setRentday(4);
        rentcar.setPrice(1200);

        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("rentDate", v.getPropertyPath().toString());
    }

}








