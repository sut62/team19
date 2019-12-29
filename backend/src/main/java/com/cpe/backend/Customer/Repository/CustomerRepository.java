package com.cpe.backend.Customer.Repository;

import com.cpe.backend.Customer.Entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findById(long id);
}