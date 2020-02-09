package com.cpe.backend.FileSharing.Entity;
import com.cpe.backend.RentCar.Entity.RentCar;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.Collection;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @SequenceGenerator(name="employee_seq",sequenceName="employee_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq")
    @Column(name = "EMPLOYEE_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @NotNull
    private String name;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Collection<RentCar> rent;
}