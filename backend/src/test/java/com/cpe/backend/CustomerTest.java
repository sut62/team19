package com.cpe.backend;

import com.cpe.backend.Customer.Entity.Customer;
import com.cpe.backend.Customer.Repository.CustomerRepository;
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
import java.time.LocalDateTime;


@DataJpaTest
public class CustomerTest {

    private Validator validator;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6015107_testCustomerOK() {
        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setDate(LocalDateTime.now());

        customer = customerRepository.saveAndFlush(customer);

        Optional<Customer> found = customerRepository.findById(customer.getId());
        assertEquals("Nattapol Panta", found.get().getName());
        assertEquals("1234567890123", found.get().getCard_num());
        assertEquals(21, found.get().getAge());
        assertEquals("149/29 suranaree p.saraburi", found.get().getAddress());
        assertEquals("0812345678", found.get().getTel());
    }

    //==============================================================================
    //==============================================================================
    // Test for Name
    @Test
    void B6015107_testNameMustNotBeNull() {
        Customer customer = new Customer();
        customer.setName(null);
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }
    @Test
    void B6015107_testNameMustMatchString() {
        Customer customer = new Customer();
        customer.setName("12345678 12345678");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must match \"^[A-Za-z ]{1,30}$\"", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }
    @Test
    void B6015107_testNameMustBeMore30String() {
        Customer customer = new Customer();
        customer.setName("NattapolNattapolNattapolPantaPantaPantaname");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must match \"^[A-Za-z ]{1,30}$\"", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    //==============================================================================
    //==============================================================================
    // Test for Card_num
    @Test
    void B6015107_testCard_NumMustNotBeNull() {
        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num(null); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("card_num", v.getPropertyPath().toString());
    }
    @Test
    void B6015107_testCard_NumMustNotBe12Digits() {
        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("123456789012"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must match \"\\d{13}\"", v.getMessage());
        assertEquals("card_num", v.getPropertyPath().toString());
    }
    @Test
    void B6015107_testCard_numMustNotBe14Digits() {
        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("12345678901234"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must match \"\\d{13}\"", v.getMessage());
        assertEquals("card_num", v.getPropertyPath().toString());
    }
    @Test
    void B6015107_testCard_numMustNotDigits() {
        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("abcdefghijklm"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must match \"\\d{13}\"", v.getMessage());
        assertEquals("card_num", v.getPropertyPath().toString());
    }
    @Test
    void testCard_numMustBeUnique() {
        // สร้าง Card_num object
        Customer c1 = new Customer();
        c1.setName("Nattapol Panta");
        c1.setCard_num("1234567890123");
        c1.setAge(21);
        c1.setAddress("149/29 suranaree p.saraburi");
        c1.setTel("0812345678");
        c1.setDate(LocalDateTime.now());

        customerRepository.saveAndFlush(c1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // สร้าง Card_num object ตัวที่ 2
            Customer c2 = new Customer();
            c2.setName("Nattapol Panta");
            c2.setCard_num("1234567890123");
            c2.setAge(21);
            c2.setAddress("149/29 suranaree p.saraburi");
            c2.setTel("0812345678");
            c2.setDate(LocalDateTime.now());
            customerRepository.saveAndFlush(c2);
        });
    }

    //==============================================================================
    //==============================================================================
    // Test for Age
    @Test
    void B6015107_testAgeMustNotBeNull() {
        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(null);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("age", v.getPropertyPath().toString());
    }
    @Test
    void B6015107_testAgeMustBeGreaterThan20() {
        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(18);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must be greater than or equal to 20", v.getMessage());
        assertEquals("age", v.getPropertyPath().toString());
    }

    @Test
    void B6015107_testAgeMustBeLessThan70() {
        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(123);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must be less than or equal to 70", v.getMessage());
        assertEquals("age", v.getPropertyPath().toString());
    }


    //==============================================================================
    //==============================================================================
    // Test for Address
    @Test
    void B6015107_testAddressMustNotBeNull() {
        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress(null);
        customer.setTel("0812345678");
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("address", v.getPropertyPath().toString());
    }
    @Test
    void B6015107_testAddressMustMatch() {
        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi149/29 suranaree p.saraburi149/29 suranaree p.saraburi149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must match \"^[0-9a-zA-Z -./]{1,50}$\"", v.getMessage());
        assertEquals("address", v.getPropertyPath().toString());
    }

    //==============================================================================
    //==============================================================================
    // Test for Tel
    @Test
    void B6015107_testTelMustNotBeNull() {
        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel(null);
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }
    @Test
    void B6015107_testTelMustNotBe9Digits() {
        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("081234567");
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }
    @Test
    void B6015107_testTelMustNotBe11Digits() {
        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("08123456789");
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }
    @Test
    void B6015107_testTelMustNotDigits() {
        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("abcdefghij");
        customer.setDate(LocalDateTime.now());

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }
    //==============================================================================
    //==============================================================================
    // Test for Date
    @Test
    void B6015107_testDateMustNotBeNull() {
        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setDate(null);

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());
    }


}


















