package com.cpe.backend.RentCar.Entity;
// import com.cpe.backend.FileSharing.entity.Employee;
// import com.cpe.backend.Carname.entity.Car;
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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import com.fasterxml.jackson.annotation.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "RENT_CAR")
public class RentCar {
    @Id
    @SequenceGenerator(name = "rent_car_seq", sequenceName = "rent_car_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rent_car_seq")
    @Column(name = "RENT_CAR_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name = "RENT_DATE")
    private @NonNull LocalDateTime rentDate;
    private @NonNull Integer rentday;
    private @NonNull Integer price;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RentType.class)
    @JoinColumn(name = "RENTTYPE_ID", insertable = true)
    @JsonManagedReference
    private RentType type;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", insertable = true)
    private Customer rentalBy;

    /*@ManyToOne(fetch = FetchType.EAGER, targetEntity = Car.class)
    @JoinColumn(name = "CAR_ID", insertable = true)
    private Car selectcar;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee createdby;*/
}
