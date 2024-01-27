package com.example.demo.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
@Data
@Table(name= "monopatin")
public class Monopatin implements Serializable {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMonopatin;


	@Column
	private Long ubicacion;

	@Column
	private String estado;
	
	@Column
	private boolean requiereMantenimiento;
	
	@OneToMany
	@JsonIgnoreProperties("idMonopatin")
	private List <Viaje> viajes;
	

	public Monopatin () {} //Constructor por defecto para que STS no tenga prob de instanciacion

	public Monopatin (Long ubicacion, String estado) {
		
		this.ubicacion = ubicacion;
		this.estado = estado;
		this.requiereMantenimiento= false;
		this.viajes = new ArrayList <>();
		
		
	}
	
	public void requiereMantenimiento() {
		double kilometrosTotales=0;
		for (Viaje viaje: viajes) {
			kilometrosTotales = kilometrosTotales + viaje.getKilometros();
			
		}
		if (kilometrosTotales>5000) {
			this.requiereMantenimiento=true;
			
		}
	
	}
	
	
}
