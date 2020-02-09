package com.cpe.backend.RentCar.Entity;

import javax.persistence.*;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="RENTTYPE")
public class RentType {
    @Id
    @SequenceGenerator(name="renttype_seq",sequenceName="renttype_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="renttype_seq")
    @Column(name="RENTTYPE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @NotNull
    private Integer unitprice;

    @NotNull
    private String nametype;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Collection<RentCar> rent;
}
