    
package com.cpe.backend;

import com.cpe.backend.Payment.entity.PaymentOptions;

import com.cpe.backend.Payment.repository.OptionsRepository;

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
public class PaymentOptionsTest {

    private Validator validator;

    @Autowired
    private OptionsRepository optionsRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    //======================================================================
    //=                             [ Test PaymentOptions]                 =
    //======================================================================

    @Test
    void b6008611_test_InsertDataOk() {
        PaymentOptions name = new PaymentOptions();

        name.setName("ชำระเงินสด");
        name = optionsRepository.saveAndFlush(name);

        Optional<PaymentOptions> found = optionsRepository.findById(name.getId());
        assertEquals(name, found.get());

    }


    @Test
    void b6008611_test_PaymentOptionsMustNotBeNull() {
        PaymentOptions name = new PaymentOptions();

        name.setName(null);
        
        Set<ConstraintViolation<PaymentOptions>> result = validator.validate(name);
        assertEquals(1, result.size());

        ConstraintViolation<PaymentOptions> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

   

}