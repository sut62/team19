package com.cpe.backend.Carreturn.Repository;

import com.cpe.backend.Carreturn.Entity.ReturnsCar;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Collection;

@RepositoryRestResource
public
interface ReturnsCarRepository extends JpaRepository<ReturnsCar, Long> {
	ReturnsCar findById(long id);
	 @Query( value = "SELECT * FROM ReturnsCar o  join Rent_car r join Customer c where o.RENT_CAR_ID = r.RENT_CAR_ID and  r.CUSTOMER_ID = :username",
            nativeQuery = true)
    Collection<ReturnsCar > findByRentcar(@Param("username") String username);
}