package com.cpe.backend.Customer.Entity;

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

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="JOB")
public class Job {

    @Id
    @SequenceGenerator(name="JOB_seq",sequenceName="JOB_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="JOB_seq")
    @Column(name = "JOB_ID", unique = true, nullable = true)
    @NonNull
    private Long id;
    
    @NotNull
    private String job;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Customer> cus;

}
