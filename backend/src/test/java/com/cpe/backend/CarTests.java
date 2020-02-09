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
public class CarTests {

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
        Car vin = new Car();
        vin.setVin("A4567890123");
        vin.setPlate("บพ4525");
        vin.setCarbrand(carbrand);
        vin.setPlateprovince(province);
        vin.setCarseat(carseat);
        vin.setCreatedby(createdby);
        vin = carRepository.saveAndFlush(vin);

        // error message ตรงชนิด และถูก field
        Optional<Car> found = carRepository.findById(vin.getId());
        assertEquals("A4567890123", found.get().getVin());
        assertEquals("บพ4525", found.get().getPlate());
        assertEquals(carbrand, found.get().getCarbrand());
        assertEquals(province, found.get().getPlateprovince());
        assertEquals(carseat, found.get().getCarseat());
        assertEquals(createdby, found.get().getCreatedby());
    }
    @Test
    void b6000950_testSizeMin() {
        Brand carbrand = brandRepository.findById(1);
        Carseat carseat = carseatRepository.findById(1);
        Province province = provinceRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        Car vincar = new Car();
        vincar.setVin("1234567890");
        vincar.setPlate("บพ4525");
        vincar.setCarbrand(carbrand);
        vincar.setPlateprovince(province);
        vincar.setCarseat(carseat);
        vincar.setCreatedby(createdby);
        Set<ConstraintViolation<Car>> result = validator.validate(vincar);
        assertEquals(1, result.size());
        ConstraintViolation<Car> v = result.iterator().next();
        assertEquals("size must be between 11 and 17", v.getMessage());
        assertEquals("vin",v.getPropertyPath().toString());
    }
      @Test
    void b6000950_testSizeMax() {
        Brand carbrand = brandRepository.findById(1);
        Carseat carseat = carseatRepository.findById(1);
        Province province = provinceRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        Car vincar = new Car();
        vincar.setVin("123456789012345678");
        vincar.setPlate("บพ4525");
        vincar.setCarbrand(carbrand);
        vincar.setPlateprovince(province);
        vincar.setCarseat(carseat);
        vincar.setCreatedby(createdby);
        Set<ConstraintViolation<Car>> result = validator.validate(vincar);
        assertEquals(1, result.size());
        ConstraintViolation<Car> v = result.iterator().next();
        assertEquals("size must be between 11 and 17", v.getMessage());
        assertEquals("vin",v.getPropertyPath().toString());
    }


    @Test
    void b6000950_Testnotnull() {
        Brand carbrand = brandRepository.findById(1);
        Carseat carseat = carseatRepository.findById(1);
        Province province = provinceRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        Car vincar = new Car();
        vincar.setVin(null);
        vincar.setPlate("บพ4525");
        vincar.setCarbrand(carbrand);
        vincar.setPlateprovince(province);
        vincar.setCarseat(carseat);
        vincar.setCreatedby(createdby);
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
        Brand carbrand = brandRepository.findById(1);
        Carseat carseat = carseatRepository.findById(1);
        Province province = provinceRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        Car p1 = new Car();
        p1.setVin("12345678901");
        p1.setPlate("บพ4525");
        p1.setCarbrand(carbrand);
        p1.setPlateprovince(province);
        p1.setCarseat(carseat);
        p1.setCreatedby(createdby);
        carRepository.saveAndFlush(p1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // สร้าง person object ตัวที่ 2
        
            Car p2 = new Car();
            p2.setVin("12345678901");
            p2.setPlate("บพ4525");
            p2.setCarbrand(carbrand);
            p2.setPlateprovince(province);
            p2.setCarseat(carseat);
            p2.setCreatedby(createdby);
            carRepository.saveAndFlush(p2);
        });
    }
    //======================================================================
    //=                             [ Test carbrand]                       =
    //======================================================================
    @Test
    void b6000950_test_carbrandMustNotBeNull() {
        
        Carseat carseat = carseatRepository.findById(1);
        Province province = provinceRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        

       
        Car car = new Car();
        car.setVin("456245845624");
        car.setPlate("ขฟ7895");
        car.setCarbrand(null);
        car.setPlateprovince(province);
        car.setCarseat(carseat);
        car.setCreatedby(createdby);
        



        Set<ConstraintViolation<Car>> result = validator.validate(car);
        assertEquals(1, result.size());

        ConstraintViolation<Car> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("carbrand", v.getPropertyPath().toString());
    }
     //======================================================================
    //=                             [ Test carseat]                       =
    //======================================================================
    @Test
    void b6000950_test_carseatMustNotBeNull() {
        Brand brand = brandRepository.findById(1);
        Province province = provinceRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        

       
        Car car = new Car();
        car.setVin("456245845624");
        car.setPlate("ขฟ7895");
        car.setCarbrand(brand);
        car.setPlateprovince(province);
        car.setCarseat(null);
        car.setCreatedby(createdby);
        



        Set<ConstraintViolation<Car>> result = validator.validate(car);
        assertEquals(1, result.size());

        ConstraintViolation<Car> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("carseat", v.getPropertyPath().toString());
    }
 //======================================================================
    //=                             [ Test plateprovince]                       =
    //======================================================================
    @Test
    void b6000950_test_plateprovinceMustNotBeNull() {
        
        Brand brand = brandRepository.findById(1);
        Carseat carseat = carseatRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        

       
        Car car = new Car();
        car.setVin("456245845624");
        car.setPlate("ขฟ7895");
        car.setCarbrand(brand);
        car.setPlateprovince(null);
        car.setCarseat(carseat);
        car.setCreatedby(createdby);
        



        Set<ConstraintViolation<Car>> result = validator.validate(car);
        assertEquals(1, result.size());

        ConstraintViolation<Car> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("plateprovince", v.getPropertyPath().toString());
    }
 //======================================================================
    //=                             [ Test createdby]                       =
    //======================================================================
    @Test
    void b6000950_test_createdbyMustNotBeNull() {
        
       Brand brand = brandRepository.findById(1);
        Carseat carseat = carseatRepository.findById(1);
        Province province = provinceRepository.findById(1);
        
       
        Car car = new Car();
        car.setVin("456245845624");
        car.setPlate("ขฟ7895");
        car.setCarbrand(brand);
        car.setPlateprovince(province);
        car.setCarseat(carseat);
        car.setCreatedby(null);
        



        Set<ConstraintViolation<Car>> result = validator.validate(car);
        assertEquals(1, result.size());

        ConstraintViolation<Car> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("createdby", v.getPropertyPath().toString());
    }



}


















