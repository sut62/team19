package com.cpe.backend.Customer.Repository;

import com.cpe.backend.Customer.Entity.Job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface JobRepository extends JpaRepository<Job, Long> {
	Job findById(long id);
}