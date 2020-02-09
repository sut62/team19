package com.cpe.backend;

import com.cpe.backend.Carname.Entity.Car;
import com.cpe.backend.Carname.Repository.CarRepository;
import com.cpe.backend.Carname.Entity.Brand;
import com.cpe.backend.Carname.Repository.BrandRepository;
import com.cpe.backend.Carname.Entity.Carseat;
import com.cpe.backend.Carname.Repository.CarseatRepository;
import com.cpe.backend.Carname.Entity.Province;
import com.cpe.backend.Carname.Repository.ProvinceRepository;
import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.FileSharing.Repository.EmployeeRepository;
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

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CarseatRepository carseatRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProvinceRepository provinceRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
     @Test
    void b6000950_TestBeOk() {
        Brand carbrand = brandRepository.findById(1);
        Carseat carseat = carseatRepository.findById(1);
        Province province = provinceRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        Car plate = new Car();
        plate.setPlate("บฟ4685");
        plate.setVin("A1234567890123");
        plate.setCarbrand(carbrand);
        plate.setPlateprovince(province);
        plate.setCarseat(carseat);
        plate.setCreatedby(createdby);
        plate = carRepository.saveAndFlush(plate);

        // error message ตรงชนิด และถูก field
        Optional<Car> found = carRepository.findById(plate.getId());
         assertEquals("บฟ4685", found.get().getPlate());
        assertEquals("A1234567890123", found.get().getVin());
        assertEquals(carbrand, found.get().getCarbrand());
        assertEquals(province, found.get().getPlateprovince());
        assertEquals(carseat, found.get().getCarseat());
        assertEquals(createdby, found.get().getCreatedby());

    }
 @Test
    void b6000950_Testnotnull() {
        Brand carbrand = brandRepository.findById(1);
        Carseat carseat = carseatRepository.findById(1);
        Province province = provinceRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        Car carplate = new Car();
        carplate.setPlate(null);
        carplate.setVin("A1234567890123");
        carplate.setCarbrand(carbrand);
        carplate.setPlateprovince(province);
        carplate.setCarseat(carseat);
        carplate.setCreatedby(createdby);
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
        Brand carbrand = brandRepository.findById(1);
        Carseat carseat = carseatRepository.findById(1);
        Province province = provinceRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        Car carplate = new Car();
        carplate.setPlate("ก78445");
        carplate.setVin("A1234567890123");
        carplate.setCarbrand(carbrand);
        carplate.setPlateprovince(province);
        carplate.setCarseat(carseat);
        carplate.setCreatedby(createdby);

        Set<ConstraintViolation<Car>> result = validator.validate(carplate);

        assertEquals(1, result.size());
        ConstraintViolation<Car> message = result.iterator().next();
        assertEquals("must match \"[ก-ฮ][ก-ฮ]\\d{4}\"",message.getMessage());
        assertEquals("plate",message.getPropertyPath().toString());
    }
    @Test
    void b6000950_testPattrenPlateLetter(){
        Brand carbrand = brandRepository.findById(1);
        Carseat carseat = carseatRepository.findById(1);
        Province province = provinceRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        Car carplate = new Car();
        carplate.setPlate("กกก445");
        carplate.setVin("A1234567890123");
        carplate.setCarbrand(carbrand);
        carplate.setPlateprovince(province);
        carplate.setCarseat(carseat);
        carplate.setCreatedby(createdby);

        Set<ConstraintViolation<Car>> result = validator.validate(carplate);

        assertEquals(1, result.size());
        ConstraintViolation<Car> message = result.iterator().next();
        assertEquals("must match \"[ก-ฮ][ก-ฮ]\\d{4}\"",message.getMessage());
        assertEquals("plate",message.getPropertyPath().toString());
    }
     
     @Test
    void b6000950_testPattrenPlatenotmacth(){
        Brand carbrand = brandRepository.findById(1);
        Carseat carseat = carseatRepository.findById(1);
        Province province = provinceRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        Car carplate = new Car();
        carplate.setPlate("กก74458");
        carplate.setVin("A1234567890123");
        carplate.setCarbrand(carbrand);
        carplate.setPlateprovince(province);
        carplate.setCarseat(carseat);
        carplate.setCreatedby(createdby);

        Set<ConstraintViolation<Car>> result = validator.validate(carplate);

        assertEquals(1, result.size());
        ConstraintViolation<Car> message = result.iterator().next();
        assertEquals("must match \"[ก-ฮ][ก-ฮ]\\d{4}\"",message.getMessage());
        assertEquals("plate",message.getPropertyPath().toString());
    }
    
    

}