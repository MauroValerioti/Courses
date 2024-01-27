package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Viaje;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MonopatinPorKilometrosDTO {
	
	
	private Long idMonopatin;


	private Long ubicacion;

	
	private String estado;


}
