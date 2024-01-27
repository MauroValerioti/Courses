package com.example.microusuarios.controller;



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

import com.example.microusuarios.Service.UsuarioService;
import com.example.microusuarios.model.Usuario;
import com.example.microusuarios.response.UsuarioResponseRest;

@RestController
@RequestMapping("api/v1") //URL general
public class UsuarioControllerJPA {
	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public ResponseEntity<UsuarioResponseRest> getUsuarios() {		
		ResponseEntity<UsuarioResponseRest> response = usuarioService.getAll();
		return response;
		
	}
		
	@GetMapping("/usuario/{id}")
	public ResponseEntity<UsuarioResponseRest> getUsuarioById(@PathVariable Long id) {	
		ResponseEntity<UsuarioResponseRest> response = usuarioService.getById(id);
		return response;
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<UsuarioResponseRest> save(@RequestBody Usuario usuario) {
		
		ResponseEntity<UsuarioResponseRest> response = usuarioService.save(usuario);
		return response;
	}
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<UsuarioResponseRest> update(@RequestBody Usuario usuario, @PathVariable Long id) {
		ResponseEntity<UsuarioResponseRest> response = usuarioService.updateById(usuario, id);
		return response;
	}
	
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<UsuarioResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<UsuarioResponseRest> response = usuarioService.deleteById(id);
		return response;
	}
	
	@PostMapping("/usuario/{idUsuario}/vincularCuenta/{idCuenta}")
	public ResponseEntity<UsuarioResponseRest> vincularCuentaAUsuario(
	    @PathVariable("idUsuario") Long idUsuario,
	    @PathVariable("idCuenta") Long idCuenta
	) {
	    ResponseEntity<UsuarioResponseRest> response = usuarioService.vincularCuentaUsuario(idUsuario, idCuenta);
	    return response;
	}
	
	@PostMapping("/usuario/{idUsuario}/desvincularCuenta/{idCuenta}")
	public ResponseEntity<UsuarioResponseRest> desVincularCuentaAUsuario(
	    @PathVariable("idUsuario") Long idUsuario,
	    @PathVariable("idCuenta") Long idCuenta
	) {
	    ResponseEntity<UsuarioResponseRest> response = usuarioService.desVincularCuentaUsuario(idUsuario, idCuenta);
	    return response;
	}
	
}
