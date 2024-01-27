package com.example.demo.repository;


import com.example.demo.dto.MonopatinPorKilometrosDTO;
import com.example.demo.model.Monopatin;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface MonopatinRepository extends CrudRepository<Monopatin, Long> {
	
	/*@Query("SELECT new com.example.demo.dto.MonopatinPorKilometrosDTO(m.idMonopatin, SUM(v.kilometros) as totalKilometros) FROM Monopatin m "
			+ "JOIN m.viajes v "
			+ "WHERE m.idMonopatin = :idMonopatin")
		  
	public List<MonopatinPorKilometrosDTO> getMonopatinPorKilometrosDTO(@Param("idMonopatin") Long idMonopatin);
	
	*/
	

	/*
	SELECT m.idMonopatin, SUM(v.kilometros) as totalKilometros
	FROM Monopatin m
	JOIN m.viajes v
	WHERE m.idMonopatin = :idMonopatin
	GROUP BY m.idMonopatin*/
	
}