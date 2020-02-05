package com.cpe.backend.RentCar.Entity;
import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.Carname.Entity.Car;
import com.cpe.backend.Customer.Entity.Customer;

import javax.persistence.*;
import lombok.*;
import java.lang.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import com.fasterxml.jackson.annotation.*;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Data
@Entity
@NoArgsConstructor
@Table(name = "RENT_CAR")
public class RentCar {
    @Id
    @SequenceGenerator(name = "rent_car_seq", sequenceName = "rent_car_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rent_car_seq")
    @Column(name = "RENT_CAR_ID", unique = true, nullable = true)
    private Long id;

    @NotNull
    @Column(name = "RENT_DATE")
    private LocalDateTime rentDate;

    @NotNull
    @PositiveOrZero
    @Max(value = 366)
    private Integer rentday;

    @NotNull
    @Max(value = 999999999)
    @Min(value = 600)
    private Integer price;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RentType.class)
    @JoinColumn(name = "RENTTYPE_ID", insertable = true)
    @JsonManagedReference
    @NotNull
    private RentType type;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", insertable = true)
    @NotNull
    private Customer rentalBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Car.class)
    @JoinColumn(name = "CAR_ID", insertable = true)
    @NotNull
    private Car selectcar;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    @NotNull
    private Employee createdby;
}
