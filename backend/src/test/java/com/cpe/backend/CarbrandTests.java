package com.cpe.backend;


import com.cpe.backend.Carname.Entity.Brand;
import com.cpe.backend.Carname.Repository.BrandRepository;
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
public class CarbrandTests {

    private Validator validator;
   
    @Autowired
    private BrandRepository brandRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
     @Test
    void b6000950_TestCarbrandBeOk() {
        
        Brand brand = new Brand();
        brand.setBrand("Toyota Vigo");
       
        brand = brandRepository.saveAndFlush(brand);

        // error message ตรงชนิด และถูก field
        Optional<Brand> found = brandRepository.findById(brand.getId());
        assertEquals("Toyota Vigo", found.get().getBrand());
       
    }
    @Test
    void b6000950_testCarbrandNotnull() {
        
        Brand brand = new Brand();
        brand.setBrand(null);
        
        Set<ConstraintViolation<Brand>> result = validator.validate(brand);
        assertEquals(1, result.size());
        ConstraintViolation<Brand> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("brand",v.getPropertyPath().toString());
    }
     

}