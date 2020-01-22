package com.cpe.backend.RentCar.Repository;
import com.cpe.backend.RentCar.Entity.RentCar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

@CrossOrigin(origins = "http://localhost:8081")
@RepositoryRestResource
public interface RentCarRepository extends JpaRepository<RentCar, Long> {
	RentCar findById(long id);
	@Query( value = "SELECT * FROM Rent_Car c where c.Customer_ID = :id",
            nativeQuery = true)
    Collection<RentCar> findByIdCustomer(@Param("id") Long id);
}
