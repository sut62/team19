package com.cpe.backend;


import com.cpe.backend.Customer.Entity.RegisType;
import com.cpe.backend.Customer.Repository.RegisTypeRepository;

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
public class RegisTypeTest {

    private Validator validator;

    @Autowired
    private RegisTypeRepository regisTypeRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6015107_testRegisTypeBeOK() {
        RegisType type = new RegisType();
        type.setType("VIP");

        type = regisTypeRepository.saveAndFlush(type);

        Optional<RegisType> found = regisTypeRepository.findById(type.getId());
        assertEquals("VIP", found.get().getType());

    }
    @Test
    void B6015107_testRegisTypeMustNotBeNull() {
        RegisType type = new RegisType();
        type.setType(null);

        Set<ConstraintViolation<RegisType>> result = validator.validate(type);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RegisType> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());
    }
}