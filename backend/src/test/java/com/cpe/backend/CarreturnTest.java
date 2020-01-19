package com.cpe.backend;

import com.cpe.backend.Carreturn.Entity.ReturnsCar;
import com.cpe.backend.Carreturn.Repository.ReturnsCarRepository;
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
public class CarreturnTest {

    private Validator validator;

    @Autowired
    private ReturnsCarRepository returnsCarRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    LocalDateTime returndate = LocalDateTime.now();
    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test

    @Test
    void b6010317_test_NoteMustBeNull() {
       ReturnsCar returnsCar = new ReturnsCar();
        returnsCar.setReturndate(returndate);
        returnsCar.setNote(null);

        Set<ConstraintViolation<ReturnsCar>> result = validator.validate(returnsCar);
        assertEquals(1, result.size());

        ConstraintViolation<ReturnsCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }

    @Test
    void b6010317_test_ReturndateMustBeNull() {
       ReturnsCar returnsCar = new ReturnsCar();
        returnsCar.setReturndate(null);
        returnsCar.setNote("กันชนหลังแตก");

        Set<ConstraintViolation<ReturnsCar>> result = validator.validate(returnsCar);
        assertEquals(1, result.size());

        ConstraintViolation<ReturnsCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("returndate", v.getPropertyPath().toString());
    }

    @Test
    void b6010317_testInsertInformationOk() {
        // สร้าง
        ReturnsCar returnsCar = new ReturnsCar();

        // เซ็ตค่าต่างๆ
       returnsCar.setReturndate(returndate);
       returnsCar.setNote("กระจกข้างเสียหาย");
       returnsCar = returnsCarRepository.saveAndFlush(returnsCar);

        Optional<ReturnsCar> found = returnsCarRepository.findById(returnsCar.getId());
        assertEquals(returnsCar, found.get());

    }

    @Test
    void b6010317_testMaxsize() {
        // สร้าง
        ReturnsCar returnsCar = new ReturnsCar();

        // เซ็ตค่าต่างๆ
       returnsCar.setReturndate(returndate);
       returnsCar.setNote("กขฅคฅฆงจฉชซฌญฎฏฐฑฒณดตถทธนบปผฝพฟภมยรลวศษศหฬอฮabcdefghijklmnopqrstuvwxyzz");

       Set<ConstraintViolation<ReturnsCar>> result = validator.validate(returnsCar);
        assertEquals(1, result.size());

       ConstraintViolation<ReturnsCar> violation = result.iterator().next();
        assertEquals("error", violation.getMessage());
        assertEquals("note", violation.getPropertyPath().toString());

    }


}








