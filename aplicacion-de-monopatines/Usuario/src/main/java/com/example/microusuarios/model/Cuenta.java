package com.example.microusuarios.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name= "cuenta")
public class Cuenta implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCuenta;
	
	@Column
	private LocalDate fechaAlta;
	
	@Column
	private double saldo;
	
	@Column
	
	private boolean activa;
	

	@ManyToMany(fetch = FetchType.LAZY)
	/*@JsonBackReference*/
	@JsonIgnoreProperties("cuentas") // Ignora la propiedad "cuentas" al serializar
	private List<Usuario> usuarios;
	
	public Cuenta() {
		this.fechaAlta = LocalDate.now();
		this.activa = true;
		this.usuarios = new ArrayList<>();	
	}

	public Cuenta(double saldo) {
		super();
		this.saldo = saldo;
		this.fechaAlta = LocalDate.now();
		this.activa = true;
		this.usuarios = new ArrayList<>();
	}


}
