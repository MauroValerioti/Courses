package com.example.demo.model;

import java.time.LocalDate;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name= "tarifa")
public class Tarifa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTarifa;

	@Column
	private double tarifaNormal;
	
	@Column
	private double tarifaDiferencial;
	
	@Column
	private LocalDate fechaVigencia;
	


	

}
