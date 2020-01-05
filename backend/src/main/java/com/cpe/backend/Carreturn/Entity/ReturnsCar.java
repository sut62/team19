package com.cpe.backend.Carreturn.Entity;
import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.RentCar.Entity.RentCar;
import com.cpe.backend.Customer.Entity.Customer;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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


@Data
@Entity
@NoArgsConstructor
@Table(name="RETURNSCAR")
public class ReturnsCar {

    @Id
    @SequenceGenerator(name="returnscar_seq",sequenceName="returnscar_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="returnscar_seq")
    @Column(name = "RETURNSCAR_ID", unique = true, nullable = true)
    private @NonNull Long id;
   
    @Column(name="RETURNDATE")
    private @NonNull LocalDateTime returndate;
    @Column(name="ANNOTATION")
    private @NonNull String note;



    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee createdby;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = RentCar.class)
    @JoinColumn(name = "RENT_CAR_ID", insertable = true)
    private RentCar rents;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Payforfine.class)
    @JoinColumn(name = "PAYFORFINE_ID", insertable = true)
    private Payforfine pays;
}