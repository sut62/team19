package com.cpe.backend.RentCar.Repository;
import com.cpe.backend.RentCar.Entity.RentCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface RentCarRepository extends JpaRepository<RentCar, Long> {
	RentCar findById(long id);
}
