package com.cpe.backend.Payment.entity;

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
import javax.validation.constraints.NotNull;


@Data
@Entity
@NoArgsConstructor
@Table(name="PaymentOptions")
public class PaymentOptions {

    @Id
    @SequenceGenerator(name="options_seq",sequenceName="options_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="options_seq")
    @Column(name = "OPTIONS_ID", unique = true, nullable = true)
    private Long id;
    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Payment> pay;

}


