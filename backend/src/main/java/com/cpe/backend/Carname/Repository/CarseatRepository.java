package com.cpe.backend.Carname.Repository;

import com.cpe.backend.Carname.Entity.Carseat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CarseatRepository extends JpaRepository<Carseat, Long> {
	Carseat findById(long id);
}