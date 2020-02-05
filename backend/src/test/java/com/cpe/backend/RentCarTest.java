package com.cpe.backend;

import com.cpe.backend.Customer.Entity.Customer;
import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.RentCar.Entity.RentCar;
import com.cpe.backend.Carname.Entity.Car;
import com.cpe.backend.RentCar.Entity.RentType;

import com.cpe.backend.RentCar.Repository.RentTypeRepository;
import com.cpe.backend.Customer.Repository.CustomerRepository;
import com.cpe.backend.FileSharing.Repository.EmployeeRepository;
import com.cpe.backend.RentCar.Repository.RentCarRepository;
import com.cpe.backend.Carname.Repository.CarRepository;

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
public class RentCarTest {

    private Validator validator;

    @Autowired
    private RentCarRepository rentcarRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RentTypeRepository rentTypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    LocalDateTime rentDate = LocalDateTime.now();

    
    @Test
    void b6004804_test_InsertDataNormal() {
        Customer rentalby = customerRepository.findById(1);
        Car car = carRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        RentType types = rentTypeRepository.findById(1);

        RentCar rentcar = new RentCar();
        rentcar.setRentDate(rentDate);
        rentcar.setRentday(4);
        rentcar.setPrice(12000);
        rentcar.setRentalBy(rentalby);
        rentcar.setSelectcar(car);
        rentcar.setCreatedby(createdby);
        rentcar.setType(types);

        rentcar = rentcarRepository.saveAndFlush(rentcar);

        Optional<RentCar> found = rentcarRepository.findById(rentcar.getId());
        assertEquals(4, found.get().getRentday());
    }
    //======================================================================
    //=                             [ Test rentday ]                       =
    //======================================================================
    @Test
    void b6004804_test_RentdayMustNotBeNull() {
        Customer rentalby = customerRepository.findById(1);
        Car car = carRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        RentType types = rentTypeRepository.findById(1);

        System.out.println("\n=======================================");
        System.out.println("\nTest rentday Must Not Be Null");
        System.out.println("\n=======================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(rentDate);
        rentcar.setRentday(null);
        rentcar.setPrice(12000);
        rentcar.setRentalBy(rentalby);
        rentcar.setSelectcar(car);
        rentcar.setCreatedby(createdby);
        rentcar.setType(types);

        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("rentday", v.getPropertyPath().toString());
    }

    @Test
    void b6004804_test_RentdayMustBePositiveOrZero() {
        Customer rentalby = customerRepository.findById(1);
        Car car = carRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        RentType types = rentTypeRepository.findById(1);

        System.out.println("\n==========================================");
        System.out.println("\nTest rentday Must Be Positive Or Zero");
        System.out.println("\n==========================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(rentDate);
        rentcar.setRentday(-1);
        rentcar.setPrice(12000);
        rentcar.setRentalBy(rentalby);
        rentcar.setSelectcar(car);
        rentcar.setCreatedby(createdby);
        rentcar.setType(types);


        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must be greater than or equal to 0", v.getMessage());
        assertEquals("rentday", v.getPropertyPath().toString());
    }

    @Test
    void b6004804_test_RentdayMustBeMax() {
        Customer rentalby = customerRepository.findById(1);
        Car car = carRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        RentType types = rentTypeRepository.findById(1);

        System.out.println("\n=======================================");
        System.out.println("\nTest rentday Must Be Max");
        System.out.println("\n=======================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(LocalDateTime.now());
        rentcar.setRentday(367);
        rentcar.setPrice(12000);
        rentcar.setRentalBy(rentalby);
        rentcar.setSelectcar(car);
        rentcar.setCreatedby(createdby);
        rentcar.setType(types);


        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must be less than or equal to 366", v.getMessage());
        assertEquals("rentday", v.getPropertyPath().toString());
    }

    //======================================================================
    //=                             [ Test price ]                       =
    //======================================================================
    @Test
    void b6004804_test_PriceMustNotBeNull() {
        Customer rentalby = customerRepository.findById(1);
        Car car = carRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        RentType types = rentTypeRepository.findById(1);

        System.out.println("\n=======================================");
        System.out.println("\nTest price Must Not Be Null");
        System.out.println("\n=======================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(rentDate);
        rentcar.setRentday(4);
        rentcar.setPrice(null);
        rentcar.setRentalBy(rentalby);
        rentcar.setSelectcar(car);
        rentcar.setCreatedby(createdby);
        rentcar.setType(types);


        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());
    }

    @Test
    void b6004804_test_PriceMustBeMin() {
        Customer rentalby = customerRepository.findById(1);
        Car car = carRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        RentType types = rentTypeRepository.findById(1);

        System.out.println("\n==========================================");
        System.out.println("\nTest price Must Be Min");
        System.out.println("\n==========================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(rentDate);
        rentcar.setRentday(4);
        rentcar.setPrice(599);
        rentcar.setRentalBy(rentalby);
        rentcar.setSelectcar(car);
        rentcar.setCreatedby(createdby);
        rentcar.setType(types);


        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must be greater than or equal to 600", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());
    }

    @Test
    void b6004804_test_PriceMustBeMax() {
        Customer rentalby = customerRepository.findById(1);
        Car car = carRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        RentType types = rentTypeRepository.findById(1);

        System.out.println("\n=======================================");
        System.out.println("\nTest price Must Be Max");
        System.out.println("\n=======================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(rentDate);
        rentcar.setRentday(4);
        rentcar.setPrice(1234567890);
        rentcar.setRentalBy(rentalby);
        rentcar.setSelectcar(car);
        rentcar.setCreatedby(createdby);
        rentcar.setType(types);


        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must be less than or equal to 999999999", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());
    }

    //======================================================================
    //=                             [ Test rentDate]                       =
    //======================================================================
    @Test
    void b6004804_test_rentDateMustNotBeNull() {
        Customer rentalby = customerRepository.findById(1);
        Car car = carRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        RentType types = rentTypeRepository.findById(1);

        System.out.println("\n=======================================");
        System.out.println("\nTest rentDate Must Not Be Null");
        System.out.println("\n=======================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(null);
        rentcar.setRentday(4);
        rentcar.setPrice(1200);
        rentcar.setRentalBy(rentalby);
        rentcar.setSelectcar(car);
        rentcar.setCreatedby(createdby);
        rentcar.setType(types);


        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("rentDate", v.getPropertyPath().toString());
    }


    //======================================================================
    //=                             [ Test Type]                       =
    //======================================================================
    @Test
    void b6004804_test_TypeMustNotBeNull() {
        Customer rentalby = customerRepository.findById(1);
        Car car = carRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);

        System.out.println("\n=======================================");
        System.out.println("\nTest Type Must Not Be Null");
        System.out.println("\n=======================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(rentDate);
        rentcar.setRentday(4);
        rentcar.setPrice(1200);
        rentcar.setRentalBy(rentalby);
        rentcar.setSelectcar(car);
        rentcar.setCreatedby(createdby);
        rentcar.setType(null);


        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());
    }


    //======================================================================
    //=                             [ Test rentalBy]                       =
    //======================================================================
    @Test
    void b6004804_test_rentalByMustNotBeNull() {
        Car car = carRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        RentType types = rentTypeRepository.findById(1);

        System.out.println("\n=======================================");
        System.out.println("\nTest rentalBy Must Not Be Null");
        System.out.println("\n=======================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(rentDate);
        rentcar.setRentday(4);
        rentcar.setPrice(1200);
        rentcar.setRentalBy(null);
        rentcar.setSelectcar(car);
        rentcar.setCreatedby(createdby);
        rentcar.setType(types);


        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("rentalBy", v.getPropertyPath().toString());
    }

    //======================================================================
    //=                             [ Test selectcar]                       =
    //======================================================================
    @Test
    void b6004804_test_selectcarMustNotBeNull() {
        Customer rentalby = customerRepository.findById(1);
        Employee createdby = employeeRepository.findById(1);
        RentType types = rentTypeRepository.findById(1);

        System.out.println("\n=======================================");
        System.out.println("\nTest selectcar Must Not Be Null");
        System.out.println("\n=======================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(rentDate);
        rentcar.setRentday(4);
        rentcar.setPrice(1200);
        rentcar.setRentalBy(rentalby);
        rentcar.setSelectcar(null);
        rentcar.setCreatedby(createdby);
        rentcar.setType(types);


        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("selectcar", v.getPropertyPath().toString());
    }

    //======================================================================
    //=                             [ Test createdby]                       =
    //======================================================================
    @Test
    void b6004804_test_createdbyMustNotBeNull() {
        Customer rentalby = customerRepository.findById(1);
        Car car = carRepository.findById(1);
        RentType types = rentTypeRepository.findById(1);

        System.out.println("\n=======================================");
        System.out.println("\nTest createdby Must Not Be Null");
        System.out.println("\n=======================================\n");
        RentCar rentcar = new RentCar();
        rentcar.setRentDate(rentDate);
        rentcar.setRentday(4);
        rentcar.setPrice(1200);
        rentcar.setRentalBy(rentalby);
        rentcar.setSelectcar(car);
        rentcar.setCreatedby(null);
        rentcar.setType(types);


        Set<ConstraintViolation<RentCar>> result = validator.validate(rentcar);
        assertEquals(1, result.size());

        ConstraintViolation<RentCar> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("createdby", v.getPropertyPath().toString());
    }
}








