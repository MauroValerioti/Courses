package com.example.microusuarios.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.microusuarios.model.Cuenta;

//ACA VAN LAS QUERYS ESPECIFICAS- extends JpaRepository<Monopatin, Integer>. Lo cambie para poder usar optional
public interface CuentaRepository extends CrudRepository<Cuenta, Long> {
		

}
