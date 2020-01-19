package com.cpe.backend;

import com.cpe.backend.Maintenance.Entity.Maintenance;
import com.cpe.backend.Maintenance.Repository.MaintenanceRepository;
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
public class MaintenanceTest {

    private Validator validator;

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    LocalDateTime fixdate = LocalDateTime.now();
    
    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void b6012328_testInputNormalData() {
        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(fixdate);
        maintenance.setMessageBox("เปลี่ยนยาง");
        maintenance.setAutoPart("ยางหลังฝั่งซ้าย");
        maintenance.setMileage((long)863463);

        maintenance = maintenanceRepository.saveAndFlush(maintenance);

        Optional<Maintenance> found = maintenanceRepository.findById(maintenance.getId());
        
    }

    @Test
    void b6012328_testMessageMustNotBeNull() {
        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(fixdate);
        maintenance.setMessageBox(null);
        maintenance.setAutoPart("ยางหลังฝั่งซ้าย");
        maintenance.setMileage((long)34366);

        Set<ConstraintViolation<Maintenance>> result = validator.validate(maintenance);
        assertEquals(1, result.size());

        ConstraintViolation<Maintenance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("messageBox", v.getPropertyPath().toString());
    }

    @Test
    void b6012328_testAutopartMustNotBeNull() {
        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(fixdate);
        maintenance.setMessageBox("เปลี่ยนยาง");
        maintenance.setAutoPart(null);
        maintenance.setMileage((long)34366);

        Set<ConstraintViolation<Maintenance>> result = validator.validate(maintenance);
        assertEquals(1, result.size());

        ConstraintViolation<Maintenance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("autoPart", v.getPropertyPath().toString());
    }

    @Test
    void b6012328_testMileageMustNotBeNull() {
        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(fixdate);
        maintenance.setMessageBox("เปลี่ยนยาง");
        maintenance.setAutoPart("ยางหลังฝั่ซ้าย");
        maintenance.setMileage(null);

        Set<ConstraintViolation<Maintenance>> result = validator.validate(maintenance);
        assertEquals(1, result.size());

        ConstraintViolation<Maintenance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("mileage", v.getPropertyPath().toString());
    }


    @Test
    void b6012328_testMileageMustBePositiveOrZero() {
        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(fixdate);
        maintenance.setMessageBox("เปลี่ยนยาง");
        maintenance.setAutoPart("ยางหลังฝั่งซ้าย");
        maintenance.setMileage((long)-1);

        Set<ConstraintViolation<Maintenance>> result = validator.validate(maintenance);
        assertEquals(1, result.size());

        ConstraintViolation<Maintenance> v = result.iterator().next();
        assertEquals("must be greater than or equal to 0", v.getMessage());
        assertEquals("mileage", v.getPropertyPath().toString());
    }

    @Test
    void b6012328_testDateMustNotBeNull() {
        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(null);
        maintenance.setMessageBox("เปลี่ยนยาง");
        maintenance.setAutoPart("ยางหลังฝั่งซ้าย");
        maintenance.setMileage((long)34366);

        Set<ConstraintViolation<Maintenance>> result = validator.validate(maintenance);
        assertEquals(1, result.size());

        ConstraintViolation<Maintenance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("fixdate", v.getPropertyPath().toString());
    }

}








