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

import com.example.microusuarios.Service.CuentaService;
import com.example.microusuarios.model.Cuenta;
import com.example.microusuarios.response.CuentaResponseRest;

@RestController
@RequestMapping("api/v1") //URL general
public class CuentaControllerJPA {
	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private CuentaService cuentaService;

	@GetMapping("/cuentas")
	public ResponseEntity<CuentaResponseRest> getCuentas() {		
		ResponseEntity<CuentaResponseRest> response = cuentaService.getAll();
		return response;
		
	}
		
	@GetMapping("/cuenta/{id}")
	public ResponseEntity<CuentaResponseRest> getCuentasById(@PathVariable Long id) {	
		ResponseEntity<CuentaResponseRest> response = cuentaService.getById(id);
		return response;
	}
	
	@PostMapping("/cuenta")
	public ResponseEntity<CuentaResponseRest> save(@RequestBody Cuenta cuenta) {
		
		ResponseEntity<CuentaResponseRest> response = cuentaService.save(cuenta);
		return response;
	}
	
	@PutMapping("/cuenta/{id}")
	public ResponseEntity<CuentaResponseRest> update(@RequestBody Cuenta cuenta, @PathVariable Long id) {
		ResponseEntity<CuentaResponseRest> response = cuentaService.updateById(cuenta, id);
		return response;
	}
	
	@DeleteMapping("/cuenta/{id}")
	public ResponseEntity<CuentaResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<CuentaResponseRest> response = cuentaService.deleteById(id);
		return response;
	}
	
	@PutMapping("/desactivarCuenta/{id}")
	public ResponseEntity<CuentaResponseRest> desactivarCuenta(@PathVariable Long id) {
		ResponseEntity<CuentaResponseRest> response = cuentaService.desactivarCuenta(id);
		return response;
	}
	 
}
