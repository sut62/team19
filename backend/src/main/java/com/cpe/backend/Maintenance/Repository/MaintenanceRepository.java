package com.cpe.backend.Maintenance.Repository;
import com.cpe.backend.Maintenance.Entity.Maintenance;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.query.Param;
import java.util.Collection;

@RepositoryRestResource
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
	Maintenance findById(long id);
	@Query( value = "SELECT * FROM Maintenanece o JOIN Car c where o.CAR_ID = c.CAR_ID and c.PLATE = :platee",
	nativeQuery = true)
	Collection<Maintenance> findMaintenanceByPlate(@Param("platee") String platee);
}
