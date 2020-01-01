package com.cpe.backend.Maintenance.Repository;
import com.cpe.backend.Maintenance.Entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
	Maintenance findById(long id);
}
