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

@Data
@Entity
@NoArgsConstructor
@Table(name="RESGISTYPE")
public class RegisType {

    @Id
    @SequenceGenerator(name="RegisType_seq",sequenceName="RegisType_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RegisType_seq")
    @Column(name = "RegisType_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String type;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Customer> cus;

}
