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
public class CarTests {

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
        Car vin = new Car();
        vin.setVin("A4567890123");
        vin.setPlate("บพ4525");
        vin = carRepository.saveAndFlush(vin);

        // error message ตรงชนิด และถูก field
        Optional<Car> found = carRepository.findById(vin.getId());
        assertEquals("A4567890123", found.get().getVin());
    }
    @Test
    void b6000950_testSize() {
        Car vincar = new Car();
        vincar.setVin("1234567890");
        vincar.setPlate("บพ4525");
        Set<ConstraintViolation<Car>> result = validator.validate(vincar);
        assertEquals(1, result.size());
        ConstraintViolation<Car> v = result.iterator().next();
        assertEquals("size must be between 11 and 17", v.getMessage());
        assertEquals("vin",v.getPropertyPath().toString());
    }

    @Test
    void b6000950_Testnotnull() {
        Car vincar = new Car();
        vincar.setVin(null);
        vincar.setPlate("บพ4525");
        Set<ConstraintViolation<Car>> result = validator.validate(vincar);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Car> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("vin",v.getPropertyPath().toString());
    }

    @Test
    void b6000950_testVinMustBeUnique() {
        // สร้าง person object
        Car p1 = new Car();
        p1.setVin("12345678901");
        p1.setPlate("บพ4525");
        carRepository.saveAndFlush(p1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // สร้าง person object ตัวที่ 2
            Car p2 = new Car();
            p2.setVin("12345678901");
            p2.setPlate("บพ4525");
            carRepository.saveAndFlush(p2);
        });
    }
 

}


















