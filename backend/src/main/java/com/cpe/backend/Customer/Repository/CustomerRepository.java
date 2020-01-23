package com.cpe.backend.Customer.Repository;

import com.cpe.backend.Customer.Entity.Customer;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.query.Param;
import java.util.Collection;

@RepositoryRestResource
public
interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findById(long id);
	@Query( value = "SELECT * FROM Customer x where x.name = :name",
            nativeQuery = true)
    Collection<Customer> findCustomerByName(@Param("name") String name);
}