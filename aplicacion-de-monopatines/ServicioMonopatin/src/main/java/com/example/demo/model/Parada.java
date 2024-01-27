package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name= "parada")
public class Parada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idParada;

	@Column
	private Long ubicacion;
	

}
