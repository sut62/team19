package com.cpe.backend.Carreturn.Repository;

import com.cpe.backend.Carreturn.Entity.ReturnsCar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ReturnsCarRepository extends JpaRepository<ReturnsCar, Long> {
	ReturnsCar findById(long id);
}