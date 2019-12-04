package com.okta.Car.Carreturn.entity;
import com.okta.Car.FileSharing.entity.Employee;
import com.okta.Car.CarRental.entity.RentCar;
import com.okta.Car.Customer.entity.Customer;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


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