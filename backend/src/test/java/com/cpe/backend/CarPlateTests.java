package com.cpe.backend;

import com.cpe.backend.Carname.Entity.Car;
import com.cpe.backend.Carname.Repository.CarRepository;
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
public class CarPlateTests {

    private Validator validator;

    @Autowired
    private CarRepository carRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
     @Test
    void b6000950_TestBeOk() {
        Car plate = new Car();
        plate.setPlate("บฟ4685");
        plate.setVin("A1234567890123");
        plate = carRepository.saveAndFlush(plate);

        // error message ตรงชนิด และถูก field
        Optional<Car> found = carRepository.findById(plate.getId());
        assertEquals("บฟ4685", found.get().getPlate());
    }
 @Test
    void b6000950_Testnotnull() {
        Car carplate = new Car();
        carplate.setPlate(null);
        carplate.setVin("A1234567890123");
        Set<ConstraintViolation<Car>> result = validator.validate(carplate);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Car> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("plate",v.getPropertyPath().toString());
    }
@Test
    void b6000950_testPattrenPlateNumber(){
        Car carplate = new Car();
        carplate.setPlate("ก78445");
        carplate.setVin("A1234567890123");

        Set<ConstraintViolation<Car>> result = validator.validate(carplate);

        assertEquals(1, result.size());
        ConstraintViolation<Car> message = result.iterator().next();
        assertEquals("must match \"[ก-ฮ][ก-ฮ]\\d{4}\"",message.getMessage());
        assertEquals("plate",message.getPropertyPath().toString());
    }
    @Test
    void b6000950_testPattrenPlateLetter(){
        Car carplate = new Car();
        carplate.setPlate("กกก445");
        carplate.setVin("A1234567890123");

        Set<ConstraintViolation<Car>> result = validator.validate(carplate);

        assertEquals(1, result.size());
        ConstraintViolation<Car> message = result.iterator().next();
        assertEquals("must match \"[ก-ฮ][ก-ฮ]\\d{4}\"",message.getMessage());
        assertEquals("plate",message.getPropertyPath().toString());
    }
    

}