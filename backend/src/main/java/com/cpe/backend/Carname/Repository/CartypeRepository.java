package com.cpe.backend.Carname.Repository;

import com.cpe.backend.Carname.Entity.Cartype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CartypeRepository extends JpaRepository<Cartype, Long> {
	Cartype findById(long id);
}