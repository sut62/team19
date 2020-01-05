package com.cpe.backend.Payment.Repository;

import com.cpe.backend.Payment.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findById(long id);
}