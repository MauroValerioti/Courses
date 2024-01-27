package com.example.microusuarios.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.microusuarios.model.Usuario;

//ACA VAN LAS QUERYS ESPECIFICAS- extends JpaRepository<Monopatin, Integer>. Lo cambie para poder usar optional
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
		

}
