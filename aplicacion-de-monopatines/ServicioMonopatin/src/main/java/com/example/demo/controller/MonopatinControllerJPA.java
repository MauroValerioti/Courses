package com.example.demo.controller;


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
import com.example.demo.Service.MonopatinService;

import com.example.demo.model.Monopatin;
import com.example.demo.response.MonopatinResponseRest;

//ACA SE MAPEAN LOS METODOS DEL SERVICE CON REST

@RestController
@RequestMapping("api/v1") //URL general
public class MonopatinControllerJPA {

	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private MonopatinService monopatinService;

	@GetMapping("/monopatines")
	public ResponseEntity<MonopatinResponseRest> getMonopatines() {		
		ResponseEntity<MonopatinResponseRest> response = monopatinService.getAll();
		return response;
		
	}
		
	@GetMapping("/monopatin/{id}")
	public ResponseEntity<MonopatinResponseRest> getMonopatinById(@PathVariable Long id) {	
		ResponseEntity<MonopatinResponseRest> response = monopatinService.getById(id);
		return response;
	}
	
	@PostMapping("/monopatin")
	public ResponseEntity<MonopatinResponseRest> save(@RequestBody Monopatin monopatin) {
		
		ResponseEntity<MonopatinResponseRest> response = monopatinService.save(monopatin);
		return response;
	}
	
	@PutMapping("/monopatin/{id}")
	public ResponseEntity<MonopatinResponseRest> update(@RequestBody Monopatin monopatin, @PathVariable Long id) {
		ResponseEntity<MonopatinResponseRest> response = monopatinService.updateById(monopatin, id);
		return response;
	}
	
	@DeleteMapping("/monopatin/{id}")
	public ResponseEntity<MonopatinResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<MonopatinResponseRest> response = monopatinService.deleteById(id);
		return response;
	}
	
	/*@GetMapping("/monopatin/{id}")
	public ResponseEntity<MonopatinResponseRest> reporteKilometrosMonopatin(@PathVariable Long id) {	
		ResponseEntity<MonopatinResponseRest> response = monopatinService.getKilometrosById(id);
		return response;
	}*/
	
	@GetMapping("/mantenimientoMonopatines")
	public ResponseEntity<MonopatinResponseRest> mantenimientoMonopatines() {	
		ResponseEntity<MonopatinResponseRest> response = monopatinService.getEstadoMonopatines();
		return response;
	}
	
	 
}
