package com.cpe.backend;

import com.cpe.backend.Carreturn.Entity.Payforfine;

import com.cpe.backend.Carreturn.Repository.PayforfineRepository;

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
public class PayforfineTest {

    private Validator validator;

    @Autowired
    private PayforfineRepository payforfineRepository;



    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    //======================================================================
    //=                             [ Test saveinformation ]               =
    //======================================================================

     @Test
    void b6010317_testInsertInformationOK() {
        // สร้าง
        Payforfine payforfine = new Payforfine();
        // เซ็ตค่าต่างๆ

       payforfine.setDescription("ไม่มีการเสียหาย");
       payforfine = payforfineRepository.saveAndFlush(payforfine);


        Optional<Payforfine> found = payforfineRepository.findById(payforfine.getId());
        assertEquals(payforfine, found.get());

    }

    //======================================================================
    //=                             [ Test Payforfine ]                    =
    //======================================================================

     @Test
    void b6010317_test_PayforfineMustNotBeNull() {
       Payforfine payforfine = new Payforfine();
        payforfine.setDescription(null);
     
        Set<ConstraintViolation<Payforfine>> result = validator.validate(payforfine);
        assertEquals(1, result.size());

        ConstraintViolation<Payforfine> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("description", v.getPropertyPath().toString());
    }


}








