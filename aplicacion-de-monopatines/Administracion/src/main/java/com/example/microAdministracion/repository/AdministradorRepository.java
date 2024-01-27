package com.example.microAdministracion.repository;


import org.springframework.data.repository.CrudRepository;

import com.example.microAdministracion.model.Administrador;


//ACA VAN LAS QUERYS ESPECIFICAS- extends JpaRepository<Administrador, Integer>. Lo cambie para poder usar optional
public interface AdministradorRepository extends CrudRepository<Administrador, Long> {
	
	

	
}