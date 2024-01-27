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
import com.example.demo.Service.ParadaService;
import com.example.demo.model.Parada;
import com.example.demo.response.ParadaResponseRest;

//ACA SE MAPEAN LOS METODOS DEL SERVICE CON REST

@RestController
@RequestMapping("api/v1") //URL general
public class ParadaControllerJPA {

	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private ParadaService paradaService;

	@GetMapping("/paradas")
	public ResponseEntity<ParadaResponseRest> getParadas() {		
		ResponseEntity<ParadaResponseRest> response = paradaService.getAll();
		return response;
		
	}
		
	@GetMapping("/parada/{id}")
	public ResponseEntity<ParadaResponseRest> getParadaById(@PathVariable Long id) {	
		ResponseEntity<ParadaResponseRest> response = paradaService.getById(id);
		return response;
	}
	
	@PostMapping("/parada")
	public ResponseEntity<ParadaResponseRest> save(@RequestBody Parada parada) {
		
		ResponseEntity<ParadaResponseRest> response = paradaService.save(parada);
		return response;
	}
	
	@PutMapping("/parada/{id}")
	public ResponseEntity<ParadaResponseRest> update(@RequestBody Parada parada, @PathVariable Long id) {
		ResponseEntity<ParadaResponseRest> response = paradaService.updateById(parada, id);
		return response;
	}
	
	@DeleteMapping("/parada/{id}")
	public ResponseEntity<ParadaResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<ParadaResponseRest> response = paradaService.deleteById(id);
		return response;
	}
	 
}
