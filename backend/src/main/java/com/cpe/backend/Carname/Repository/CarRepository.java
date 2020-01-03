package com.cpe.backend.Carname.Repository;

import com.cpe.backend.Carname.Entity.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CarRepository extends JpaRepository<Car, Long> {
	Car findById(long id);
}