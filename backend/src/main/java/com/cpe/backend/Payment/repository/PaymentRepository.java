package com.cpe.backend.Payment.repository;

import com.cpe.backend.Payment.entity.Payment;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Collection;

@RepositoryRestResource
public
interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findById(long id);
    	@Query( value = "SELECT * FROM Payment o join Rent_car r join Customer c where o.RENT_CAR_ID = r.RENT_CAR_ID and r.CUSTOMER_ID = :username",
    			nativeQuery = true)
    Collection<Payment > findByRentcar(@Param("username") String username);
}