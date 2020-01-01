package com.cpe.backend.Maintenance.Repository;
import com.cpe.backend.Maintenance.Entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public
interface ServiceRepository extends JpaRepository<Service, Long> {
	Service findById(long id);
}
