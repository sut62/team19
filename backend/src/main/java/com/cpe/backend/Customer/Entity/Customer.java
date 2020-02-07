package com.cpe.backend.Customer.Entity;
import com.cpe.backend.FileSharing.Entity.Employee;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

@Data
@Entity
@NoArgsConstructor
@Table(
    name="CUSTOMER" , 
    uniqueConstraints = @UniqueConstraint(columnNames = {"CARD_NUM"})
    )
public class Customer {

    @Id
    @SequenceGenerator(name="Customer_seq",sequenceName="Customer_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Customer_seq")
    @Column(name = "Customer_ID", unique = true, nullable = true)
    private  Long id;

    @NotNull
    @Pattern(regexp = "^[A-Za-z ]{1,30}$")
    @Column(name = "NAME")
    private String name;

    @NotNull 
    @Pattern(regexp = "\\d{13}")
    @Column(name = "CARD_NUM")
    private String card_num;

    @NotNull
    @Column(name = "AGE")
    @Min(value = 20)
    @Max(value = 70)
    private  Integer age;
    
    @NotNull
    @Column(name = "ADDRESS")
    @Pattern(regexp = "^[0-9a-zA-Z -./]{1,50}$")
    private String address;

    @NotNull 
    @Pattern(regexp = "\\d{10}")
    @Column(name = "TEL")
    private String tel;

    @NotNull
    @Column(name = "DATE")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Job.class)
    @JoinColumn(name = "JOB", insertable = true)
    @NotNull
    private Job work;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE", insertable = true)
    @NotNull
    private Employee createdBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RegisType.class)
    @JoinColumn(name = "REGISTYPE", insertable = true)
    @NotNull
    private RegisType type;


}