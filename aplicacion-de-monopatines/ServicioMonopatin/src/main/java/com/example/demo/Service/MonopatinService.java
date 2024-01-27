package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.MonopatinPorKilometrosDTO;
import com.example.demo.model.Monopatin;
import com.example.demo.model.Viaje;
import com.example.demo.response.MonopatinResponseRest;
import com.example.demo.repository.MonopatinRepository;

@Service
public class MonopatinService {
	
	@Autowired
	MonopatinRepository monopatinRepository;
	
	
	
	@Transactional(readOnly=true)
	public ResponseEntity<MonopatinResponseRest> getAll() {
		
		MonopatinResponseRest response = new MonopatinResponseRest();
		try {
			List<Monopatin> monopatin = (List<Monopatin>)monopatinRepository.findAll();
			response.getMonopatinResponse().setMonopatin(monopatin);
			response.setMetadaData("Respuesta ok", "00", "Respuesta exitosa");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional(readOnly=true)
	public ResponseEntity<MonopatinResponseRest> getById(Long id) {
		
		MonopatinResponseRest response = new MonopatinResponseRest();
		List <Monopatin> list = new ArrayList<>();
		try {
			Optional<Monopatin> monopatin = monopatinRepository.findById(id);
			if (monopatin.isPresent()) {
				list.add(monopatin.get());
				response.getMonopatinResponse().setMonopatin(list);
				response.setMetadaData("ok", "00", "Monopatin encontrado");
			}
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Transactional
	public ResponseEntity<MonopatinResponseRest> save(Monopatin monopatin) {
		MonopatinResponseRest response = new MonopatinResponseRest();
		List <Monopatin> list = new ArrayList<>();
		
		try {
			Monopatin monopatinSaved = monopatinRepository.save(monopatin);
			if (monopatinSaved != null) {
				list.add(monopatinSaved);
				response.getMonopatinResponse().setMonopatin(list);
				response.setMetadaData("ok", "00", "Monopatin guardado");
			}
			else {
				response.setMetadaData("nok", "-1", "Monopatin no guardado");
				return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		}
		
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al grabar monopatin");
			e.getStackTrace();
			
			}
		
		return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<MonopatinResponseRest> updateById(Monopatin monopatin, Long id) {
		MonopatinResponseRest response = new MonopatinResponseRest();
		List <Monopatin> list = new ArrayList<>();
		try {
			Optional<Monopatin> monopatinSearch = monopatinRepository.findById(id);
			if (monopatinSearch.isPresent()) {
				monopatinSearch.get().setViajes(monopatin.getViajes());
				monopatinSearch.get().setUbicacion(monopatin.getUbicacion());
				monopatinSearch.get().setEstado(monopatin.getEstado());
				Monopatin monopatinToUpdate = monopatinRepository.save(monopatinSearch.get());
				if (monopatinToUpdate != null) {
					list.add(monopatinToUpdate);
					response.getMonopatinResponse().setMonopatin(list);
					response.setMetadaData("ok", "00", "Monopatin actualizado");		
				}				
				else {
					response.setMetadaData("nok", "-1", "Monopatin no actualizado");
					return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}
			else {
				response.setMetadaData("nok", "-1", "Monopatin no encontrado");
				return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.NOT_FOUND);
			}			
		}		
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al actualizar monopatin");
			e.getStackTrace();
			
			}		
		return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.OK);
	}


	@Transactional
	public ResponseEntity<MonopatinResponseRest> deleteById(Long id) {
		MonopatinResponseRest response = new MonopatinResponseRest();
		try {
			monopatinRepository.deleteById(id);
			response.setMetadaData("Respuesta ok", "00", "Registro eliminado");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.OK);
	}
/*
	@Transactional
	public double getKilometrosById(Long id) {
		MonopatinResponseRest response = new MonopatinResponseRest();
		List <Viaje> viajes = new ArrayList<>();
		double totalKilometros = 0;
		try {
			Optional<Monopatin> monopatinSearch = monopatinRepository.findById(id);			
			if (monopatinSearch.isPresent()) {
				/*Monopatin monopatin = (Monopatin) monopatinSearch.get().getViajes();
				
				  for (Viaje viaje : viajes) {
			            totalKilometros += viaje.getKilometros();
			      }

				/*response.getMonopatinResponse().setMonopatin(list);
				response.setMetadaData("ok", "00", "Kilometros sumados");
			}
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
		/*	return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);*/

	/*@Transactional
	public ResponseEntity<MonopatinResponseRest> getEstadoMonopatines() {
		MonopatinResponseRest response = new MonopatinResponseRest();
		try {
			List<Monopatin> monopatines = (List<Monopatin>)monopatinRepository.findAll();
			
			for (Monopatin monopatin : monopatines) {
				double kilometrosTotales=0;
				List<Viaje> viajes = monopatin.getViajes();
				for (Viaje viaje: viajes) {
					kilometrosTotales = kilometrosTotales + viaje.getKilometros();
					
				}
				if (kilometrosTotales>5000) {
					monopatin.setRequiereMantenimiento(true);
					
				}
				
			}
			monopatinRepository.saveAll(monopatines);
			response.setMetadaData("Respuesta ok", "00", "Respuesta exitosa");
			
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.OK);
		
	}*/
	@Transactional
	public ResponseEntity<MonopatinResponseRest> getEstadoMonopatines() {
	MonopatinResponseRest response = new MonopatinResponseRest();
	List<Monopatin> monopatines = (List<Monopatin>) monopatinRepository.findAll();

	for (Monopatin monopatin : monopatines) {
	    double kilometrosTotales = monopatin.getViajes().stream().mapToDouble(Viaje::getKilometros).sum();

	    if (kilometrosTotales > 5000) {
	        monopatin.setRequiereMantenimiento(true);
	    }
	}

	monopatinRepository.saveAll(monopatines); // Guarda todos los monopatines en una sola operación
	return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.OK);
	}	
		
		/*return new ResponseEntity<MonopatinResponseRest>(response, HttpStatus.OK);
		
		return totalKilometros;
	}
	
*/

}

