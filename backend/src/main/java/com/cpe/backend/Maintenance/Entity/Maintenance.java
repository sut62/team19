package com.cpe.backend.Maintenance.Entity;
import com.cpe.backend.Carname.Entity.Car;


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
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.NotNull;
@Data
@Entity
@NoArgsConstructor
@Table(name="MAINTENANECE")
public class Maintenance {

    @Id
    @SequenceGenerator(name="Maintenance_seq",sequenceName="Maintenance_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Maintenance_seq")
    @Column(name = "MAINTENANCE_ID", unique = true, nullable = true)
    private Long id;
    private @NotNull String messageBox;
    private @NotNull String autoPart;

    @PositiveOrZero
    private @NotNull Long mileage;

    @Column(name="FIXDATE")
    private @NotNull LocalDateTime fixdate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Repairman.class)
    @JoinColumn(name = "REPAIRMAN_ID", insertable = true)
    private Repairman fixedBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Car.class)
    @JoinColumn(name = "CAR_ID", insertable = true)
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Service.class)
    @JoinColumn(name = "SERVICE_ID", insertable = true)
    private Service service;  
}