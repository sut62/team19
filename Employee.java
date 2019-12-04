package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @SequenceGenerator(name="Employee_seq",sequenceName="Employee_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Employee_seq")
    @Column(name = "EMPLOYEE_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String employee;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Customer> cus;

}
