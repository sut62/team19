package com.cpe.backend;

import com.cpe.backend.Payment.entity.Payment;
import com.cpe.backend.Payment.entity.PaymentOptions;
import com.cpe.backend.Payment.repository.PaymentRepository;
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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class PaymentTest {

    private Validator validator;

    @Autowired
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    LocalDateTime date = LocalDateTime.now();
    
    @Test
    void b6008611_test_DateNotBeNull() {
        Payment payment = new Payment();
        payment.setDate(null);
        payment.setNote("ชำระเงินแล้ว");
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);
        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());
    
    }
    void b6008611_test_NoteNotBeNull() {
       Payment payment = new Payment();
        payment.setDate(date);
        payment.setNote(null);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);
        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    
    }

    @Test
    void b6008611_test_OptionsNotNull() {
        PaymentOptions options = new PaymentOptions();
        options.setName(null);
        

        Set<ConstraintViolation<PaymentOptions>> result = validator.validate(options);
        assertEquals(1, result.size());

        ConstraintViolation<PaymentOptions> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    
    }

    @Test
    void b6008611_test_InsertInformation() {
        Payment payment = new Payment();
        payment.setDate(date);
        payment.setNote("ชำระเงินแล้ว");
        payment = paymentRepository.saveAndFlush(payment);

        Optional<Payment> found = paymentRepository.findById(payment.getId());
        assertEquals(payment, found.get());

    }
    
    @Test
    void b6008611_testMaxsize() {
        Payment payment = new Payment();
       payment.setDate(date);
       payment.setNote("กขฅคฅฆงจฉชซฌญฎฏฐฑฒณดตถทธนบปผฝพฟภมยรลวศษศหฬอฮabcdefghijklmnopqrstuvwxyzz");

       Set<ConstraintViolation<Payment>> result = validator.validate(payment);
        assertEquals(1, result.size());

       ConstraintViolation<Payment> violation = result.iterator().next();
        assertEquals("error", violation.getMessage());
        assertEquals("note", violation.getPropertyPath().toString());

    }


}



