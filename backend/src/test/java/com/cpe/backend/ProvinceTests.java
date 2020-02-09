package com.cpe.backend;


import com.cpe.backend.Carname.Entity.Province;
import com.cpe.backend.Carname.Repository.ProvinceRepository;
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
public class ProvinceTests {

    private Validator validator;
   
    @Autowired
    private ProvinceRepository provinceRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
     @Test
    void b6000950_TestProvinceBeOk() {
        
        Province province = new Province();
        province.setProvince("นครราชสีมา");
       
        province = provinceRepository.saveAndFlush(province);

        // error message ตรงชนิด และถูก field
        Optional<Province> found = provinceRepository.findById(province.getId());
        assertEquals("นครราชสีมา", found.get().getProvince());
       
    }
    @Test
    void b6000950_testProvinceNotnull() {
        
       Province province = new Province();
        province.setProvince(null);
        
        Set<ConstraintViolation<Province>> result = validator.validate(province);
        assertEquals(1, result.size());
        ConstraintViolation<Province> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("province",v.getPropertyPath().toString());
    }
     

}