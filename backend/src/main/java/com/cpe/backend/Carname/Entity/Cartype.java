package com.cpe.backend.Carname.Entity;

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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@Entity
@NoArgsConstructor
@Table(name="CARTYPE")
public class Cartype {

    @Id
    @SequenceGenerator(name="cartype_seq",sequenceName="cartype_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cartype_seq")  
    @Column(name = "TYPE_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String cartype;


    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Collection<Car> car;

}
