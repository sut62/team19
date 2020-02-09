package com.cpe.backend;

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
public class EmployeeTest {

    private Validator validator;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    
    @Test
    void test_InsertDataNormal() {
        Employee employee = new Employee();
        employee.setName("Puwanat Torcheewee");
        employee.setUsername("Puwanat");
        employee.setPassword("รายวัน");

        employee = employeeRepository.saveAndFlush(employee);

        Optional<Employee> found = employeeRepository.findById(employee.getId());
        assertEquals("Puwanat Torcheewee", found.get().getName());
    }

    @Test
    void test_NameMustNotBeNull() {
        System.out.println("\n=======================================");
        System.out.println("\nTest name Must Not Be Null");
        System.out.println("\n=======================================\n");
        Employee employee = new Employee();
        employee.setName(null);
        employee.setUsername("Puwanat");
        employee.setPassword("12345678");

        Set<ConstraintViolation<Employee>> result = validator.validate(employee);
        assertEquals(1, result.size());

        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void test_UsernameMustNotBeNull() {
        System.out.println("\n=======================================");
        System.out.println("\nTest username Must Not Be Null");
        System.out.println("\n=======================================\n");
        Employee employee = new Employee();
        employee.setName("Puwanat Torcheewee");
        employee.setUsername(null);
        employee.setPassword("12345678");

        Set<ConstraintViolation<Employee>> result = validator.validate(employee);
        assertEquals(1, result.size());

        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("username", v.getPropertyPath().toString());
    }

    @Test
    void test_PasswordMustNotBeNull() {
        System.out.println("\n=======================================");
        System.out.println("\nTest password Must Not Be Null");
        System.out.println("\n=======================================\n");
        Employee employee = new Employee();
        employee.setName("Puwanat Torcheewee");
        employee.setUsername("Puwanat");
        employee.setPassword(null);

        Set<ConstraintViolation<Employee>> result = validator.validate(employee);
        assertEquals(1, result.size());

        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("password", v.getPropertyPath().toString());
    }
}








