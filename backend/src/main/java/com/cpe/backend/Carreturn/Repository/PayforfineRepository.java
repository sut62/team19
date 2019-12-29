package com.cpe.backend.Carreturn.Repository;

import com.cpe.backend.Carreturn.Entity.Payforfine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface PayforfineRepository extends JpaRepository<Payforfine, Long> {
	Payforfine findById(long id);
}