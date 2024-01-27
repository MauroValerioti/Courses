package com.example.microusuarios.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Entity
@EqualsAndHashCode
@Data
@Table(name= "usuario")
public class Usuario implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private Long nroCelular;
	@Column
	private String email;
	
	@Column
	private LocalDate fechaAlta;
	

	@ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY)
	/*@JsonBackReference*/
	@JsonIgnoreProperties("usuarios") // Ignora la propiedad "cuentas" al serializar
	private List<Cuenta> cuentas;

	public Usuario() {
		this.fechaAlta = LocalDate.now();
		this.cuentas = new ArrayList<>();
	}
	
	public Usuario(String nombre, String apellido, Long nroCelular, String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;
		this.fechaAlta = LocalDate.now();
		this.cuentas = new ArrayList<>();
		
	}

}
