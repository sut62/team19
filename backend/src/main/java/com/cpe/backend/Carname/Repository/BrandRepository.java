package com.cpe.backend.Carname.Repository;

import com.cpe.backend.Carname.Entity.Brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface BrandRepository extends JpaRepository<Brand, Long> {
	Brand findById(long id);
}