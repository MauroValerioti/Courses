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
//import com.example.demo.Service.MonopatinService;
//import com.example.demo.Service.ParadaService;
//import com.example.demo.model.Monopatin;
//import com.example.demo.model.Parada;
//import com.example.demo.response.MonopatinResponseRest;
//import com.example.demo.response.ParadaResponseRest;

import com.example.microAdministracion.Service.MantenimientoService;
import com.example.microAdministracion.model.Mantenimiento;
import com.example.microAdministracion.response.MantenimientoResponseRest;

//ACA SE MAPEAN LOS METODOS DEL SERVICE CON REST

@RestController
@RequestMapping("api/v1") //URL general
public class MantenimientoControllerJPA {

	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private MantenimientoService mantenimientoService;

	@GetMapping("/mantenimientos")
	public ResponseEntity<MantenimientoResponseRest> getMantenimientos() {		
		ResponseEntity<MantenimientoResponseRest> response = mantenimientoService.getAll();
		return response;
		
	}
		
	@GetMapping("/mantenimiento/{id}")
	public ResponseEntity<MantenimientoResponseRest> getMantenimientoById(@PathVariable Long id) {	
		ResponseEntity<MantenimientoResponseRest> response = mantenimientoService.getById(id);
		return response;
	}
	
	@PostMapping("/mantenimiento")
	public ResponseEntity<MantenimientoResponseRest> save(@RequestBody Mantenimiento mantenimiento) {
		
		ResponseEntity<MantenimientoResponseRest> response = mantenimientoService.save(mantenimiento);
		return response;
	}
	
//	@PutMapping("/mantenimiento/{id}")
//	public ResponseEntity<MantenimientoResponseRest> update(@RequestBody Mantenimiento mantenimiento, @PathVariable Long id) {
//		ResponseEntity<MantenimientoResponseRest> response = mantenimientoService.updateById(mantenimiento, id);
//		return response;
//	}
	
	@DeleteMapping("/mantenimiento/{id}")
	public ResponseEntity<MantenimientoResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<MantenimientoResponseRest> response = mantenimientoService.deleteById(id);
		return response;
	}
	 
}
