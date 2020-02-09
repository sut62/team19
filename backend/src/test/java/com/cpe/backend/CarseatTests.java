package com.cpe.backend;


import com.cpe.backend.Carname.Entity.Carseat;
import com.cpe.backend.Carname.Repository.CarseatRepository;
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
public class CarseatTests {

    private Validator validator;
   
    @Autowired
    private CarseatRepository carseatRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
     @Test
    void b6000950_TestCarseatBeOk() {
        
        Carseat seat = new Carseat();
        seat.setCarseat("2 ที่นั่ง");
       
        seat = carseatRepository.saveAndFlush(seat);

        // error message ตรงชนิด และถูก field
        Optional<Carseat> found = carseatRepository.findById(seat.getId());
        assertEquals("2 ที่นั่ง", found.get().getCarseat());
       
    }
    @Test
    void b6000950_testCarseatNotnull() {
        
       Carseat seat = new Carseat();
        seat.setCarseat(null);
        
        Set<ConstraintViolation<Carseat>> result = validator.validate(seat);
        assertEquals(1, result.size());
        ConstraintViolation<Carseat> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("carseat",v.getPropertyPath().toString());
    }
     

}