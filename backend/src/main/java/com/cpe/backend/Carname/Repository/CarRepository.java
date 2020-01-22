package com.cpe.backend.Carname.Repository;

import com.cpe.backend.Carname.Entity.Car;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.query.Param;
import java.util.Collection;

@RepositoryRestResource
public
interface CarRepository extends JpaRepository<Car, Long> {
	Car findById(long id);
	 @Query( value = "SELECT * FROM Car o where o.plate = :platee",
            nativeQuery = true)
    Collection<Car> findCarByPlate(@Param("platee") String platee);

}
	