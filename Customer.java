package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@Data
@Entity
@NoArgsConstructor
@Table(name="CUSTOMER")
public class Customer {

    @Id
    @SequenceGenerator(name="Customer_seq",sequenceName="Customer_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Customer_seq")
    @Column(name = "Customer_ID", unique = true, nullable = true)
    private @NonNull Long id;
    @Column(name="name")
    private @NonNull String name;
    private @NonNull String num_id;
    private @NonNull int age;
    @Column(name="address")
    private @NonNull  String address;
    private @NonNull String tel;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Job.class)
    @JoinColumn(name = "JOB", insertable = true)
    private Job work;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE", insertable = true)
    private Employee createdBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RegisType.class)
    @JoinColumn(name = "REGISTYPE", insertable = true)
    private RegisType type;


}