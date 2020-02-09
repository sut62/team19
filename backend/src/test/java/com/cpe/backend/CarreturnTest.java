package com.cpe.backend;

import com.cpe.backend.Carreturn.Entity.ReturnsCar;
import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.RentCar.Entity.RentCar;
import com.cpe.backend.Carreturn.Entity.Payforfine;

import com.cpe.backend.Carreturn.Repository.ReturnsCarRepository;
import com.cpe.backend.FileSharing.Repository.EmployeeRepository;
import com.cpe.backend.RentCar.Repository.RentCarRepository;
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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class CarreturnTest {

    private Validator validator;

    @Autowired
    private ReturnsCarRepository returnsCarRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RentCarRepository rentCarRepository;

    @Autowired
    private PayforfineRepository payforfineRepository;



    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    LocalDateTime returndate = LocalDateTime.now();
    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    //======================================================================
    //=                             [ Test saveinformation ]               =
    //======================================================================

     @Test
    void b6010317_testInsertInformationOk() {
        // สร้าง
        ReturnsCar returnsCar = new ReturnsCar();
        Employee createdby = employeeRepository.findById(1);
        RentCar rents = rentCarRepository.findById(1);
        Payforfine pays = payforfineRepository.findById(1);

        // เซ็ตค่าต่างๆ
       returnsCar.setReturndate(returndate);
       returnsCar.setNote("กระจกข้างเสียหาย");
       returnsCar.setCreatedby(createdby);
       returnsCar.setRents(rents);
       returnsCar.setPays(pays);
       returnsCar = returnsCarRepository.saveAndFlush(returnsCar);


        Optional<ReturnsCar> found = returnsCarRepository.findById(returnsCar.getId());
        assertEquals(returnsCar, found.get());

    }

    //======================================================================
    //=                             [ Test Note ]                          =
    //======================================================================

    @Test
    void b6010317_test_NoteMustNotBeNull() {
       ReturnsCar returnsCar = new ReturnsCar();
       Employee createdby = employeeRepository.findById(1);
       RentCar rents = rentCarRepository.findById(1);
       Payforfine pays = payforfineRepository.findById(1);

        returnsCar.setReturndate(returndate);
        returnsCar.setNote(null);
        returnsCar.setCreatedby(createdby);
        returnsCar.setRents(rents);
        returnsCar.setPays(pays);

        Set<ConstraintViolation<ReturnsCar>> result = validator.validate(returnsCar);
        assertEquals(1, result.size());

        ConstraintViolation<ReturnsCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }

    @Test
    void b6010317_testNotemorethan88() {
        // สร้าง
        ReturnsCar returnsCar = new ReturnsCar();
        Employee createdby = employeeRepository.findById(1);
        RentCar rents = rentCarRepository.findById(1);
        Payforfine pays = payforfineRepository.findById(1);

        // เซ็ตค่าต่างๆ
       returnsCar.setReturndate(returndate);
       returnsCar.setNote("กขฅคฅฆงจฉชซฌญฎฏฐฑฒณดตถทธนบปผฝพฟภมยรลวศษศหฬอฮกขฅคฅฆงจฉชซฌญฎฏฐฑฒณดตถทธนบปผฝพฟภมยรลวศษศหฬอไฮ");
       returnsCar.setCreatedby(createdby);
       returnsCar.setRents(rents);
       returnsCar.setPays(pays);

       Set<ConstraintViolation<ReturnsCar>> result = validator.validate(returnsCar);
        assertEquals(1, result.size());

       ConstraintViolation<ReturnsCar> v = result.iterator().next();
        assertEquals("size must be between 1 and 88", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());

    }

    @Test
    void b6010317_testNoteNotMatchAndNoteHave1Data() {
        // สร้าง
        ReturnsCar returnsCar = new ReturnsCar();
        Employee createdby = employeeRepository.findById(1);
        RentCar rents = rentCarRepository.findById(1);
        Payforfine pays = payforfineRepository.findById(1);

        // เซ็ตค่าต่างๆ
       returnsCar.setReturndate(returndate);
       returnsCar.setNote(" ");
       returnsCar.setCreatedby(createdby);
       returnsCar.setRents(rents);
       returnsCar.setPays(pays);

       Set<ConstraintViolation<ReturnsCar>> result = validator.validate(returnsCar);
        assertEquals(1, result.size());

       ConstraintViolation<ReturnsCar> v = result.iterator().next();
        assertEquals("must match \"^[ก-๏\\-]+$\"", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());

    }

     @Test
    void b6010317_testNotePattern() {
        // สร้าง
        ReturnsCar returnsCar = new ReturnsCar();
        Employee createdby = employeeRepository.findById(1);
        RentCar rents = rentCarRepository.findById(1);
        Payforfine pays = payforfineRepository.findById(1);

        // เซ็ตค่าต่างๆ
       returnsCar.setReturndate(returndate);
       returnsCar.setNote("Engin jam");
       returnsCar.setCreatedby(createdby);
       returnsCar.setRents(rents);
       returnsCar.setPays(pays);

       Set<ConstraintViolation<ReturnsCar>> result = validator.validate(returnsCar);
        assertEquals(1, result.size());

       ConstraintViolation<ReturnsCar> v = result.iterator().next();
        assertEquals("must match \"^[ก-๏\\-]+$\"", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());

    }

    //======================================================================
    //=                             [ Test Returndate ]                    =
    //======================================================================

     @Test
    void b6010317_test_ReturndateMustNotBeNull() {
       ReturnsCar returnsCar = new ReturnsCar();
       Employee createdby = employeeRepository.findById(1);
       RentCar rents = rentCarRepository.findById(1);
       Payforfine pays = payforfineRepository.findById(1);

        returnsCar.setReturndate(null);
        returnsCar.setNote("กันชนหลังแตก");
        returnsCar.setCreatedby(createdby);
        returnsCar.setRents(rents);
        returnsCar.setPays(pays);

        Set<ConstraintViolation<ReturnsCar>> result = validator.validate(returnsCar);
        assertEquals(1, result.size());

        ConstraintViolation<ReturnsCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("returndate", v.getPropertyPath().toString());
    }


    //======================================================================
    //=                             [ Test createdby ]                     =
    //======================================================================

     @Test
    void b6010317_test_CreatedbyMustNotBeNull() {
       ReturnsCar returnsCar = new ReturnsCar();
       Employee createdby = employeeRepository.findById(1);
       RentCar rents = rentCarRepository.findById(1);
       Payforfine pays = payforfineRepository.findById(1);

        returnsCar.setReturndate(returndate);
        returnsCar.setNote("กันชนหลังแตก");
        returnsCar.setCreatedby(null);
        returnsCar.setRents(rents);
        returnsCar.setPays(pays);

        Set<ConstraintViolation<ReturnsCar>> result = validator.validate(returnsCar);
        assertEquals(1, result.size());

        ConstraintViolation<ReturnsCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("createdby", v.getPropertyPath().toString());
    }

    //======================================================================
    //=                             [ Test rents ]                         =
    //======================================================================

     @Test
    void b6010317_test_RentsMustNotBeNull() {
       ReturnsCar returnsCar = new ReturnsCar();
       Employee createdby = employeeRepository.findById(1);
       RentCar rents = rentCarRepository.findById(1);
       Payforfine pays = payforfineRepository.findById(1);

        returnsCar.setReturndate(returndate);
        returnsCar.setNote("กันชนหลังแตก");
        returnsCar.setCreatedby(createdby);
        returnsCar.setRents(null);
        returnsCar.setPays(pays);

        Set<ConstraintViolation<ReturnsCar>> result = validator.validate(returnsCar);
        assertEquals(1, result.size());

        ConstraintViolation<ReturnsCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("rents", v.getPropertyPath().toString());
    }

    //======================================================================
    //=                             [ Test pays ]                          =
    //======================================================================

     @Test
    void b6010317_test_PaysMustNotBeNull() {
       ReturnsCar returnsCar = new ReturnsCar();
       Employee createdby = employeeRepository.findById(1);
       RentCar rents = rentCarRepository.findById(1);
       Payforfine pays = payforfineRepository.findById(1);

        returnsCar.setReturndate(returndate);
        returnsCar.setNote("กันชนหลังแตก");
        returnsCar.setCreatedby(createdby);
        returnsCar.setRents(rents);
        returnsCar.setPays(null);

        Set<ConstraintViolation<ReturnsCar>> result = validator.validate(returnsCar);
        assertEquals(1, result.size());

        ConstraintViolation<ReturnsCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("pays", v.getPropertyPath().toString());
    }

}








