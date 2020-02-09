package com.cpe.backend;

import com.cpe.backend.Payment.entity.Payment;
import com.cpe.backend.Payment.entity.PaymentOptions;
import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.RentCar.Entity.RentCar;
import com.cpe.backend.RentCar.Repository.RentCarRepository;
import com.cpe.backend.FileSharing.Repository.EmployeeRepository;
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

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RentCarRepository rentcarRepository;

    @Autowired
    private OptionsRepository optionsRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    LocalDateTime date = LocalDateTime.now();
    
    //======================================================================
    //=                             [ Test Data Ok]                        =
    //======================================================================

    @Test
    void b6008611_test_InsertDataOk() {
        Payment payment = new Payment();
        Employee createdby = employeeRepository.findById(1);
        RentCar rent = rentcarRepository.findById(1);
        PaymentOptions payoptions = optionsRepository.findById(1);

        payment.setDate(date);
        payment.setNote("ชำระเงินแล้ว");
        payment.setCreatedby(createdby);
        payment.setRent(rent);
        payment.setPayoptions(payoptions);
        payment = paymentRepository.saveAndFlush(payment);

        Optional<Payment> found = paymentRepository.findById(payment.getId());
        assertEquals(payment, found.get());

    }

    //======================================================================
    //=                             [ Test Date]                           =
    //======================================================================

    @Test
    void b6008611_test_Date_MustNotBeNull() {
        Payment payment = new Payment();
        Employee createdby = employeeRepository.findById(1);
        RentCar rent = rentcarRepository.findById(1);
        PaymentOptions payoptions = optionsRepository.findById(1);

        payment.setDate(null);
        payment.setNote("ชำระเงินแล้ว");
        payment.setCreatedby(createdby);
        payment.setRent(rent);
        payment.setPayoptions(payoptions);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);
        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());
    
    }

    //======================================================================
    //=                             [ Test Note]                        =
    //======================================================================

    @Test
    void b6008611_test_Note_MustNotBeNull() {
        Payment payment = new Payment();
        Employee createdby = employeeRepository.findById(1);
        RentCar rent = rentcarRepository.findById(1);
        PaymentOptions payoptions = optionsRepository.findById(1);

        payment.setDate(date);
        payment.setNote(null);
        payment.setCreatedby(createdby);
        payment.setRent(rent);
        payment.setPayoptions(payoptions);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);
        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    
    }


    
    
    @Test
    void b6008611_test_Note_Maxsize() {
        Payment payment = new Payment();
        Employee createdby = employeeRepository.findById(1);
        RentCar rent = rentcarRepository.findById(1);
        PaymentOptions payoptions = optionsRepository.findById(1);

        payment.setDate(date);
        payment.setNote("ฟหกดพพพพพปแผหหหหนสสวยมมมมบบลลลลจจจตคคคถภภภพพกกหหหผปปออทมสนววววววงงงบลชชชขขจจตยยยยฝฝฝฝวยสสยยนยยรรรดดอออปปผฟหหำพพพพพา");
        payment.setCreatedby(createdby);
        payment.setRent(rent);
        payment.setPayoptions(payoptions);

       Set<ConstraintViolation<Payment>> result = validator.validate(payment);
        assertEquals(1, result.size());

       ConstraintViolation<Payment> violation = result.iterator().next();
        assertEquals("size must be between 1 and 68", violation.getMessage());
        assertEquals("note", violation.getPropertyPath().toString());

    }

    @Test
    void b6008611_test_Note_Match() {
        Payment payment = new Payment();
        Employee createdby = employeeRepository.findById(1);
        RentCar rent = rentcarRepository.findById(1);
        PaymentOptions payoptions = optionsRepository.findById(1);

        payment.setDate(date);
        payment.setNote(" ");
        payment.setCreatedby(createdby);
        payment.setRent(rent);
        payment.setPayoptions(payoptions);

       Set<ConstraintViolation<Payment>> result = validator.validate(payment);
        assertEquals(1, result.size());

       ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must match \"^[ก-๏\\-]+$\"", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());

    }

    @Test
    void b6008611_test_Note_Pattern() {
        Payment payment = new Payment();
        Employee createdby = employeeRepository.findById(1);
        RentCar rent = rentcarRepository.findById(1);
        PaymentOptions payoptions = optionsRepository.findById(1);

        payment.setDate(date);
        payment.setNote("hdhuu dyyyd");
        payment.setCreatedby(createdby);
        payment.setRent(rent);
        payment.setPayoptions(payoptions);

       Set<ConstraintViolation<Payment>> result = validator.validate(payment);
        assertEquals(1, result.size());

       ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must match \"^[ก-๏\\-]+$\"", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());

    }

    //======================================================================
    //=                             [ Test payoptions]                     =
    //======================================================================

    @Test
    void b6008611_test_PayOptionsMustNotBeNull() {
        Payment payment = new Payment();
        Employee createdby = employeeRepository.findById(1);
        RentCar rent = rentcarRepository.findById(1);

        payment.setDate(date);
        payment.setNote("ชำระเงินแล้ว");
        payment.setCreatedby(createdby);
        payment.setRent(rent);
        payment.setPayoptions(null);
     
        Set<ConstraintViolation<Payment>> result = validator.validate(payment);
        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("payoptions", v.getPropertyPath().toString());
    }

    //======================================================================
    //=                             [ Test createdby]                      =
    //======================================================================

    @Test
    void b6008611_test_CreatedbyMustNotBeNull() {
        Payment payment = new Payment();
        RentCar rent = rentcarRepository.findById(1);
        PaymentOptions payoptions = optionsRepository.findById(1);

        payment.setDate(date);
        payment.setNote("ชำระเงินแล้ว");
        payment.setCreatedby(null);
        payment.setRent(rent);
        payment.setPayoptions(payoptions);
     
        Set<ConstraintViolation<Payment>> result = validator.validate(payment);
        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("createdby", v.getPropertyPath().toString());
    }

    //======================================================================
    //=                             [ Test Rent]                           =
    //======================================================================

    @Test
    void b6008611_test_RentMustNotBeNull() {
        Payment payment = new Payment();
        Employee createdby = employeeRepository.findById(1);
        PaymentOptions payoptions = optionsRepository.findById(1);

        payment.setDate(date);
        payment.setNote("ชำระเงินแล้ว");
        payment.setCreatedby(createdby);
        payment.setRent(null);
        payment.setPayoptions(payoptions);
     
        Set<ConstraintViolation<Payment>> result = validator.validate(payment);
        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("rent", v.getPropertyPath().toString());
    }


}