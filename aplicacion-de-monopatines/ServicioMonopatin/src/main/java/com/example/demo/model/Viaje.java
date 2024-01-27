package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name= "viaje")

public class Viaje implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idViaje;
	
	@Column
	private LocalDate fechaYHoraInicio;
	
	@Column
	private LocalDate fechaYHoraFin;
	
	@Column
	private LocalDate fechaYHoraInicioPausa;
	
	@Column
	private LocalDate fechaYHoraFinPausa;
	
	@Column
	private Long idMonopatin;
	
	@Column
	private double precio;

	@Column
	private double kilometros;
	
	
}
	

	