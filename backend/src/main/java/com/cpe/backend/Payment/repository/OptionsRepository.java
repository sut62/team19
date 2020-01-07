package com.cpe.backend.Payment.repository;
import com.cpe.backend.Payment.entity.PaymentOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface OptionsRepository extends JpaRepository<PaymentOptions, Long> {
	PaymentOptions findById(long id);
}


