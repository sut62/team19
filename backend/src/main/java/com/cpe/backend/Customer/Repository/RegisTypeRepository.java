package com.cpe.backend.Customer.Repository;

import com.cpe.backend.Customer.Entity.RegisType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface RegisTypeRepository extends JpaRepository<RegisType, Long> {
    RegisType findById(long id);
}