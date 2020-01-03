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
@Table(name="PLATEPROVINCE")
public class Province {

    @Id
    @SequenceGenerator(name="province_seq",sequenceName="province_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="province_seq")  
    @Column(name = "PROVINCE_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String province;


    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Collection<Car> car;

}
