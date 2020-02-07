package com.cpe.backend.Carreturn.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="PAYFORFINE")
public class Payforfine {
	@Id
	@SequenceGenerator(name="payforfine_seq",sequenceName="payforfine_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="payforfine_seq")
	@Column(name="PAYFORFINE_ID",unique = true, nullable = true)
	private  Long id;
	private @NotNull String description;

	@OneToMany(fetch = FetchType.EAGER)
	
	private Collection<ReturnsCar> returns;
}
