package com.example.microAdministracion.repository;



import org.springframework.data.repository.CrudRepository;

import com.example.microAdministracion.model.Mantenimiento;



public interface MantenimientoRepository extends CrudRepository<Mantenimiento, Long> {
	
	
}