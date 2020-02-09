package com.cpe.backend;

import com.cpe.backend.Customer.Entity.Customer;
import com.cpe.backend.Customer.Entity.Job;
import com.cpe.backend.Customer.Entity.RegisType;
import com.cpe.backend.FileSharing.Entity.Employee;

import com.cpe.backend.Customer.Repository.CustomerRepository;
import com.cpe.backend.Customer.Repository.JobRepository;
import com.cpe.backend.Customer.Repository.RegisTypeRepository;
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
import java.time.LocalDateTime;


@DataJpaTest
public class CustomerTest {

    private Validator validator;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RegisTypeRepository regisTypeRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    LocalDateTime date = LocalDateTime.now();

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6015107_testCustomerOK() {
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

        customer = customerRepository.saveAndFlush(customer);

        Optional<Customer> found = customerRepository.findById(customer.getId());
        assertEquals("Nattapol Panta", found.get().getName());
        assertEquals("1234567890123", found.get().getCard_num());
        assertEquals(21, found.get().getAge());
        assertEquals("149/29 suranaree p.saraburi", found.get().getAddress());
        assertEquals("0812345678", found.get().getTel());
        assertEquals(work, found.get().getWork());
        assertEquals(type, found.get().getType());
        assertEquals(createdBy, found.get().getCreatedBy());
        assertEquals(date, found.get().getDate());
    }

    //==============================================================================
    //==============================================================================
    // Test for Name
    @Test
    void B6015107_testNameMustNotBeNull() {
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName(null);
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

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
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("12345678 12345678");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

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
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("NattapolNattapolNattapolPantaPantaPantaname");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

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
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num(null); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

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
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("123456789012"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

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
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("12345678901234"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

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
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("abcdefghijklm"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must match \"\\d{13}\"", v.getMessage());
        assertEquals("card_num", v.getPropertyPath().toString());
    }
    @Test
    void B6015107_testCard_numMustBeUnique() {
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        // สร้าง Card_num object
        Customer c1 = new Customer();
        c1.setName("Nattapol Panta");
        c1.setCard_num("1234567890123");
        c1.setAge(21);
        c1.setAddress("149/29 suranaree p.saraburi");
        c1.setTel("0812345678");
        c1.setWork(work);
        c1.setType(type);
        c1.setCreatedBy(createdBy);
        c1.setDate(date);

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
            c2.setWork(work);
            c2.setType(type);
            c2.setCreatedBy(createdBy);
            c2.setDate(date);
            customerRepository.saveAndFlush(c2);
        });
    }

    //==============================================================================
    //==============================================================================
    // Test for Age
    @Test
    void B6015107_testAgeMustNotBeNull() {
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(null);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

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
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(18);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

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
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(123);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

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
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress(null);
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

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
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi149/29 suranaree p.saraburi149/29 suranaree p.saraburi149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

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
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel(null);
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

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
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("081234567");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

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
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("08123456789");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

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
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("abcdefghij");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);

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
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(null);

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());
    }

    //======================================================================
    //=                             [ Test work]                       =
    //======================================================================
    @Test
    void B6015107_test_workMustNotBeNull() {
        Employee createdBy = employeeRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(null);
        customer.setType(type);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);


        Set<ConstraintViolation<Customer>> result = validator.validate(customer);
        assertEquals(1, result.size());

        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("work", v.getPropertyPath().toString());
    }

    //======================================================================
    //=                             [ Test createdBy]                       =
    //======================================================================
    @Test
    void B6015107_test_createdByMustNotBeNull() {
        Job work = jobRepository.findById(1);
        RegisType type = regisTypeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(type);
        customer.setCreatedBy(null);
        customer.setDate(date);


        Set<ConstraintViolation<Customer>> result = validator.validate(customer);
        assertEquals(1, result.size());

        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("createdBy", v.getPropertyPath().toString());
    }

    //======================================================================
    //=                             [ Test type]                       =
    //======================================================================
    @Test
    void B6015107_test_typeMustNotBeNull() {
        Job work = jobRepository.findById(1);
        Employee createdBy = employeeRepository.findById(1);

        Customer customer = new Customer();
        customer.setName("Nattapol Panta");
        customer.setCard_num("1234567890123"); 
        customer.setAge(21);
        customer.setAddress("149/29 suranaree p.saraburi");
        customer.setTel("0812345678");
        customer.setWork(work);
        customer.setType(null);
        customer.setCreatedBy(createdBy);
        customer.setDate(date);


        Set<ConstraintViolation<Customer>> result = validator.validate(customer);
        assertEquals(1, result.size());

        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());
    }

}