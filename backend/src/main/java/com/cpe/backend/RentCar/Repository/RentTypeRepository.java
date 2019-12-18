package com.cpe.backend.RentCar.Repository;
import com.cpe.backend.RentCar.Entity.RentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public
interface RentTypeRepository extends JpaRepository<RentType, Long> {
	RentType findById(long id);
}
