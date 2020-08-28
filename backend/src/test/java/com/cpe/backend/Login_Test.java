package com.project.backend;

import com.project.backend.Login.Entity.Login;
import com.project.backend.Login.Repository.LoginRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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
public class Login_Test {

    private Validator validator;

    @Autowired
    private LoginRepository loginRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

	@Test
	void Test_LoginIsOK() {
        Login login = new Login();
        login.setName("Nattapol Panta");
        login.setUsername("namenp2707");
        login.setPassword("27072541");
        login.setEmail("namenp2707@hotmail.com");

        login = loginRepository.saveAndFlush(login);

        Optional<Login> found = loginRepository.findById(login.getId());
        assertEquals("Nattapol Panta", found.get().getName());
        assertEquals("namenp2707", found.get().getUsername());
        assertEquals("27072541", found.get().getPassword());
        assertEquals("namenp2707@hotmail.com", found.get().getEmail());
    }
    
    //==============================================================================
    //==============================================================================
    // ============================== Test for Name ================================
    @Test
	void Test_NameMustNotBeNull() {
        Login login = new Login();
        login.setName(null);
        login.setUsername("namenp2707");
        login.setPassword("27072541");
        login.setEmail("namenp2707@hotmail.com");

        Set<ConstraintViolation<Login>> result = validator.validate(login);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Login> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    //==============================================================================
    //==============================================================================
    // ============================== Test for Username ================================
    @Test
	void Test_UsernameMustNotBeNull() {
        Login login = new Login();
        login.setName("Nattapol Panta");
        login.setUsername(null);
        login.setPassword("27072541");
        login.setEmail("namenp2707@hotmail.com");

        Set<ConstraintViolation<Login>> result = validator.validate(login);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Login> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("username", v.getPropertyPath().toString());
    }

    //==============================================================================
    //==============================================================================
    // ============================== Test for Password ================================
    @Test
	void Test_PasswordMustNotBeNull() {
        Login login = new Login();
        login.setName("Nattapol Panta");
        login.setUsername("namenp2707");
        login.setPassword(null);
        login.setEmail("namenp2707@hotmail.com");

        Set<ConstraintViolation<Login>> result = validator.validate(login);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Login> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("password", v.getPropertyPath().toString());
    }

    //==============================================================================
    //==============================================================================
    // ============================== Test for Email ================================
    @Test
	void Test_EmailMustNotBeNull() {
        Login login = new Login();
        login.setName("Nattapol Panta");
        login.setUsername("namenp2707");
        login.setPassword("27072541");
        login.setEmail(null);

        Set<ConstraintViolation<Login>> result = validator.validate(login);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Login> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
    }

}
