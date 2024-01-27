package com.example.demo.repository;


import com.example.demo.model.Viaje;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ViajeRepository extends CrudRepository<Viaje, Long> {
	
	

	
}