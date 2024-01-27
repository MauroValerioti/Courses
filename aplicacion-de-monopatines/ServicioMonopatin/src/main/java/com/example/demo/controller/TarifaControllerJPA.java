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
import com.example.demo.Service.TarifaService;
import com.example.demo.model.Tarifa;
import com.example.demo.response.TarifaResponseRest;

//ACA SE MAPEAN LOS METODOS DEL SERVICE CON REST

@RestController
@RequestMapping("api/v1") //URL general
public class TarifaControllerJPA {

	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private TarifaService tarifaService;

	@GetMapping("/tarifas")
	public ResponseEntity<TarifaResponseRest> getTarifas() {		
		ResponseEntity<TarifaResponseRest> response = tarifaService.getAll();
		return response;
		
	}
		
	@GetMapping("/tarifa/{id}")
	public ResponseEntity<TarifaResponseRest> getTarifaById(@PathVariable Long id) {	
		ResponseEntity<TarifaResponseRest> response = tarifaService.getById(id);
		return response;
	}
	
	@PostMapping("/tarifa")
	public ResponseEntity<TarifaResponseRest> save(@RequestBody Tarifa tarifa) {		
		ResponseEntity<TarifaResponseRest> response = tarifaService.save(tarifa);
		return response;
	}
	
	@PutMapping("/tarifa/{id}")
	public ResponseEntity<TarifaResponseRest> update(@RequestBody Tarifa tarifa, @PathVariable Long id) {
		ResponseEntity<TarifaResponseRest> response = tarifaService.updateById(tarifa, id);
		return response;
	}
	
	@DeleteMapping("/tarifa/{id}")
	public ResponseEntity<TarifaResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<TarifaResponseRest> response = tarifaService.deleteById(id);
		return response;
	}
	 
}
