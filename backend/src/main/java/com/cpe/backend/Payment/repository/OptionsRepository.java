package com.cpe.backend.Payment.Repository;
import com.cpe.backend.Payment.Entity.PaymentOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface OptionsRepository extends JpaRepository<PaymentOptions, Long> {
	PaymentOptions findById(long id);
}


