package com.cpe.backend.Maintenance.Entity;

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
@Table(name="SERVICE")
public class Service {
    @Id
    @SequenceGenerator(name="SERVICE_SEQ",sequenceName="SERVICE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SERVICE_SEQ")
    @Column(name="SERVICE_ID",unique = true, nullable = true)
    private Long id;

    private @NotNull String service;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Maintenance> repair;
}