package com.example.microAdministracion.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.microAdministracion.Service.AdministradorService;
import com.example.microAdministracion.model.Administrador;
import com.example.microAdministracion.response.AdministradorResponseRest;
import com.example.microusuarios.model.Usuario;
import com.example.microusuarios.response.UsuarioResponseRest;


//ACA SE MAPEAN LOS METODOS DEL SERVICE CON REST

@RestController
@RequestMapping("api/v1") //URL general
public class AdministradorControllerJPA {

	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private AdministradorService administradorService;

	@GetMapping("/administradores")
	public ResponseEntity<AdministradorResponseRest> getAdministradores() {		
		ResponseEntity<AdministradorResponseRest> response = administradorService.getAll();
		return response;
		
	}
		
	@GetMapping("/administrador/{id}")
	public ResponseEntity<AdministradorResponseRest> getAdministradorById(@PathVariable Long id) {	
		ResponseEntity<AdministradorResponseRest> response = administradorService.getById(id);
		return response;
	}
	
	@PostMapping("/administrador")
	public ResponseEntity<AdministradorResponseRest> save(@RequestBody Administrador administrador) {
		
		ResponseEntity<AdministradorResponseRest> response = administradorService.save(administrador);
		return response;
	}
	
//	@PutMapping("/administrador/{id}")
//	public ResponseEntity<AdministradorResponseRest> update(@RequestBody Administrador administrador, @PathVariable Long id) {
//		ResponseEntity<AdministradorResponseRest> response = administradorService.updateById(administrador, id);
//		return response;
//	}
	
	@DeleteMapping("/administrador/{id}")
	public ResponseEntity<AdministradorResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<AdministradorResponseRest> response = administradorService.deleteById(id);
		return response;
	}
	
    @PostMapping("/anularCuenta/{idUsuario}/{idCuenta}")
    public ResponseEntity<UsuarioResponseRest> anularCuenta(@PathVariable Long idUsuario, @PathVariable Long idCuenta ) {
        ResponseEntity<UsuarioResponseRest> response = administradorService.anularCuenta(idUsuario, idCuenta);
        return response;
    }
	 
    @PutMapping("/desactivarCuenta/{idCuenta}")
    public ResponseEntity<UsuarioResponseRest> desactivarCuenta(@PathVariable Long idCuenta ) {
        ResponseEntity<UsuarioResponseRest> response = administradorService.desactivarCuenta(idCuenta);
        return response;
    }
    
    /*@GetMapping("/monopatines")
    public ResponseEntity<MonopatinResponseRest> getEstadoMonopatines() {
        ResponseEntity<MonopatinResponseRest> response = administradorService.requiereMantenimiento();
        return response;
    }*/
    
}
