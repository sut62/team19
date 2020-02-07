package com.cpe.backend;

import com.cpe.backend.Maintenance.Entity.Maintenance;
import com.cpe.backend.Maintenance.Entity.Repairman;
import com.cpe.backend.Maintenance.Entity.Service;
import com.cpe.backend.Carname.Entity.Car;

import com.cpe.backend.Maintenance.Repository.MaintenanceRepository;
import com.cpe.backend.Maintenance.Repository.RepairmanRepository;
import com.cpe.backend.Maintenance.Repository.ServiceRepository;
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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class MaintenanceTest {

    private Validator validator;

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private RepairmanRepository repairmanRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CarRepository carRepository;
    
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    LocalDateTime fixdate = LocalDateTime.now();
    
    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void b6012328_testInputNormalData() {
        Repairman fixedby = repairmanRepository.findById(1);
        Car car = carRepository.findById(1);
        Service service = serviceRepository.findById(1);

        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(fixdate);
        maintenance.setMessageBox("เปลี่ยนยาง");
        maintenance.setAutoPart("ยางหลังฝั่งซ้าย");
        maintenance.setMileage((long)863463);
        maintenance.setFixedBy(fixedby);
        maintenance.setService(service);
        maintenance.setCar(car);

        maintenance = maintenanceRepository.saveAndFlush(maintenance);

        Optional<Maintenance> found = maintenanceRepository.findById(maintenance.getId());
        
    }

    @Test
    void b6012328_testMessageMustNotBeNull() {
        Repairman fixedby = repairmanRepository.findById(1);
        Car car = carRepository.findById(1);
        Service service = serviceRepository.findById(1);

        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(fixdate);
        maintenance.setMessageBox(null);
        maintenance.setAutoPart("ยางหลังฝั่งซ้าย");
        maintenance.setMileage((long)34366);
        maintenance.setFixedBy(fixedby);
        maintenance.setService(service);
        maintenance.setCar(car);

        Set<ConstraintViolation<Maintenance>> result = validator.validate(maintenance);
        assertEquals(1, result.size());

        ConstraintViolation<Maintenance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("messageBox", v.getPropertyPath().toString());
    }

    @Test
    void b6012328_testAutopartMustNotBeNull() {
        Repairman fixedby = repairmanRepository.findById(1);
        Car car = carRepository.findById(1);
        Service service = serviceRepository.findById(1);

        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(fixdate);
        maintenance.setMessageBox("เปลี่ยนยาง");
        maintenance.setAutoPart(null);
        maintenance.setMileage((long)34366);
        maintenance.setFixedBy(fixedby);
        maintenance.setService(service);
        maintenance.setCar(car);

        Set<ConstraintViolation<Maintenance>> result = validator.validate(maintenance);
        assertEquals(1, result.size());

        ConstraintViolation<Maintenance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("autoPart", v.getPropertyPath().toString());
    }

    @Test
    void b6012328_testMileageMustNotBeNull() {
        Repairman fixedby = repairmanRepository.findById(1);
        Car car = carRepository.findById(1);
        Service service = serviceRepository.findById(1);

        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(fixdate);
        maintenance.setMessageBox("เปลี่ยนยาง");
        maintenance.setAutoPart("ยางหลังฝั่ซ้าย");
        maintenance.setMileage(null);
        maintenance.setFixedBy(fixedby);
        maintenance.setService(service);
        maintenance.setCar(car);

        Set<ConstraintViolation<Maintenance>> result = validator.validate(maintenance);
        assertEquals(1, result.size());

        ConstraintViolation<Maintenance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("mileage", v.getPropertyPath().toString());
    }


    @Test
    void b6012328_testMileageMustBePositiveOrZero() {
        Repairman fixedby = repairmanRepository.findById(1);
        Car car = carRepository.findById(1);
        Service service = serviceRepository.findById(1);

        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(fixdate);
        maintenance.setMessageBox("เปลี่ยนยาง");
        maintenance.setAutoPart("ยางหลังฝั่งซ้าย");
        maintenance.setMileage((long)-1);
        maintenance.setFixedBy(fixedby);
        maintenance.setService(service);
        maintenance.setCar(car);

        Set<ConstraintViolation<Maintenance>> result = validator.validate(maintenance);
        assertEquals(1, result.size());

        ConstraintViolation<Maintenance> v = result.iterator().next();
        assertEquals("must be greater than or equal to 0", v.getMessage());
        assertEquals("mileage", v.getPropertyPath().toString());
    }

    @Test
    void b6012328_testDateMustNotBeNull() {
        Repairman fixedby = repairmanRepository.findById(1);
        Car car = carRepository.findById(1);
        Service service = serviceRepository.findById(1);

        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(null);
        maintenance.setMessageBox("เปลี่ยนยาง");
        maintenance.setAutoPart("ยางหลังฝั่งซ้าย");
        maintenance.setMileage((long)34366);
        maintenance.setFixedBy(fixedby);
        maintenance.setService(service);
        maintenance.setCar(car);

        Set<ConstraintViolation<Maintenance>> result = validator.validate(maintenance);
        assertEquals(1, result.size());

        ConstraintViolation<Maintenance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("fixdate", v.getPropertyPath().toString());
    }

    @Test
    void b6012328_testMaxMessageBox() {
        Repairman fixedby = repairmanRepository.findById(1);
        Car car = carRepository.findById(1);
        Service service = serviceRepository.findById(1);

        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(fixdate);
        maintenance.setMessageBox("เปลี่ยนยางฝั่งซ้ายเพราะมันรั่วมีตะปูสิบดอกอยู่ในนั้นทำให้ไม่สามารถขับต่อไปอย่างสะดวกสบายได้อย่างที่เคยเป็นมา");
        maintenance.setAutoPart("ยางหลังฝั่งซ้าย");
        maintenance.setMileage((long)34366);
        maintenance.setFixedBy(fixedby);
        maintenance.setService(service);
        maintenance.setCar(car);

       Set<ConstraintViolation<Maintenance>> result = validator.validate(maintenance);
        assertEquals(1, result.size());

       ConstraintViolation<Maintenance> violation = result.iterator().next();
        assertEquals("error", violation.getMessage());
        assertEquals("messageBox", violation.getPropertyPath().toString());
    }

    @Test
    void b6012328_testMaxAutopart() {
        Repairman fixedby = repairmanRepository.findById(1);
        Car car = carRepository.findById(1);
        Service service = serviceRepository.findById(1);

        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(fixdate);
        maintenance.setMessageBox("เปลี่ยนยาง");
        maintenance.setAutoPart("ยางหลังฝั่งซ้ายยี่ห้อมิชเชอลินเกรดพรีเมียมนำเข้าจากสหรัฐอเมริกา");
        maintenance.setMileage((long)34366);
        maintenance.setFixedBy(fixedby);
        maintenance.setService(service);
        maintenance.setCar(car);

       Set<ConstraintViolation<Maintenance>> result = validator.validate(maintenance);
        assertEquals(1, result.size());

       ConstraintViolation<Maintenance> violation = result.iterator().next();
        assertEquals("error", violation.getMessage());
        assertEquals("autoPart", violation.getPropertyPath().toString());
    }

    @Test
    void b6012328_testFixedByMustNotBeNull() {
        Repairman fixedby = repairmanRepository.findById(1);
        Car car = carRepository.findById(1);
        Service service = serviceRepository.findById(1);

        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(fixdate);
        maintenance.setMessageBox("เปลี่ยนยาง");
        maintenance.setAutoPart("ยางหลังฝั่งซ้าย");
        maintenance.setMileage((long)34366);
        maintenance.setFixedBy(null);
        maintenance.setService(service);
        maintenance.setCar(car);

       Set<ConstraintViolation<Maintenance>> result = validator.validate(maintenance);
        assertEquals(1, result.size());

       ConstraintViolation<Maintenance> violation = result.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("fixedBy", violation.getPropertyPath().toString());
    }

    @Test
    void b6012328_testServiceMustNotBeNull() {
        Repairman fixedby = repairmanRepository.findById(1);
        Car car = carRepository.findById(1);
        Service service = serviceRepository.findById(1);

        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(fixdate);
        maintenance.setMessageBox("เปลี่ยนยาง");
        maintenance.setAutoPart("ยางหลังฝั่งซ้าย");
        maintenance.setMileage((long)34366);
        maintenance.setFixedBy(fixedby);
        maintenance.setService(null);
        maintenance.setCar(car);

       Set<ConstraintViolation<Maintenance>> result = validator.validate(maintenance);
        assertEquals(1, result.size());

       ConstraintViolation<Maintenance> violation = result.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("service", violation.getPropertyPath().toString());
    }
    
    @Test
    void b6012328_testCarMustNotBeNull() {
        Repairman fixedby = repairmanRepository.findById(1);
        Car car = carRepository.findById(1);
        Service service = serviceRepository.findById(1);

        Maintenance maintenance = new Maintenance();
        maintenance.setFixdate(fixdate);
        maintenance.setMessageBox("เปลี่ยนยาง");
        maintenance.setAutoPart("ยางหลังฝั่งซ้าย");
        maintenance.setMileage((long)34366);
        maintenance.setFixedBy(fixedby);
        maintenance.setService(service);
        maintenance.setCar(null);

       Set<ConstraintViolation<Maintenance>> result = validator.validate(maintenance);
        assertEquals(1, result.size());

       ConstraintViolation<Maintenance> violation = result.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("car", violation.getPropertyPath().toString());
    }
    

}








