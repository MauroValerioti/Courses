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
import com.example.demo.Service.ViajeService;
import com.example.demo.model.Viaje;
import com.example.demo.response.ViajeResponseRest;

//ACA SE MAPEAN LOS METODOS DEL SERVICE CON REST

@RestController
@RequestMapping("api/v1") //URL general
public class ViajeControllerJPA {

	@Autowired //Se utiliza para realizar la inyección de dependencias automáticamente
	private ViajeService viajeService;

	@GetMapping("/viajes")
	public ResponseEntity<ViajeResponseRest> getMonopatines() {		
		ResponseEntity<ViajeResponseRest> response = viajeService.getAll();
		return response;
		
	}
		
	@GetMapping("/viaje/{id}")
	public ResponseEntity<ViajeResponseRest> getMonopatinById(@PathVariable Long id) {	
		ResponseEntity<ViajeResponseRest> response = viajeService.getById(id);
		return response;
	}
	
	@PostMapping("/viaje")
	public ResponseEntity<ViajeResponseRest> save(@RequestBody Viaje viaje) {
		
		ResponseEntity<ViajeResponseRest> response = viajeService.save(viaje);
		return response;
	}
	
	@PutMapping("/viaje/{id}")
	public ResponseEntity<ViajeResponseRest> update(@RequestBody Viaje viaje, @PathVariable Long id) {
		ResponseEntity<ViajeResponseRest> response = viajeService.updateById(viaje, id);
		return response;
	}
	
	@DeleteMapping("/viaje/{id}")
	public ResponseEntity<ViajeResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<ViajeResponseRest> response = viajeService.deleteById(id);
		return response;
	}
	 
}
