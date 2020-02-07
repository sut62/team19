package com.cpe.backend.Payment.entity;
import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.RentCar.Entity.RentCar;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import com.fasterxml.jackson.annotation.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@Table(name="Payment")
public class Payment {
    @Id
    @SequenceGenerator(name="payment_seq",sequenceName="payment_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="payment_seq")
    @Column(name = "PAYMENT_ID", unique = true, nullable = true)
    private  Long id;
    
    @NotNull
    @Column(name="DATE")
    private  LocalDateTime date;

    @NotNull
    @Column(name="NOTE")
    @Size( min = 1 , max = 68 )
    @Pattern(regexp = "^[ก-๏\\-]+$")
    private  String note;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PaymentOptions.class)
    @JoinColumn(name = "OPTIONS_ID", insertable = true)
    private PaymentOptions payoptions;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RentCar.class)
    @JoinColumn(name = "RENT_CAR_ID", insertable = true)
    private RentCar rent;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee createdby;
}