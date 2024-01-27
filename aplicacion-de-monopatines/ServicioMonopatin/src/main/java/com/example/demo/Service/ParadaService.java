package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;


import com.example.demo.model.Parada;
import com.example.demo.response.ParadaResponseRest;
import com.example.demo.repository.ParadaRepository;

@Service
public class ParadaService {
	
	@Autowired
	ParadaRepository paradaRepository;
	
	@Transactional(readOnly=true)
	public ResponseEntity<ParadaResponseRest> getAll() {
		
		ParadaResponseRest response = new ParadaResponseRest();
		try {
			List<Parada> parada = (List<Parada>)paradaRepository.findAll();
			response.getParadaResponse().setParada(parada);
			response.setMetadaData("Respuesta ok", "00", "Respuesta exitosa");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<ParadaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<ParadaResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional(readOnly=true)
	public ResponseEntity<ParadaResponseRest> getById(Long id) {
		
		ParadaResponseRest response = new ParadaResponseRest();
		List <Parada> list = new ArrayList<>();
		try {
			Optional<Parada> parada = paradaRepository.findById(id);
			if (parada.isPresent()) {
				list.add(parada.get());
				response.getParadaResponse().setParada(list);
				response.setMetadaData("ok", "00", "Parada encontrada");
			}
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<ParadaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<ParadaResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Transactional
	public ResponseEntity<ParadaResponseRest> save(Parada parada) {
		ParadaResponseRest response = new ParadaResponseRest();
		List <Parada> list = new ArrayList<>();
		
		try {
			Parada paradaSaved = paradaRepository.save(parada);
			if (paradaSaved != null) {
				list.add(paradaSaved);
				response.getParadaResponse().setParada(list);
				response.setMetadaData("ok", "00", "Parada guardada");
			}
			else {
				response.setMetadaData("nok", "-1", "Parada no guardada");
				return new ResponseEntity<ParadaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		}
		
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al grabar parada");
			e.getStackTrace();
			
			}
		
		return new ResponseEntity<ParadaResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<ParadaResponseRest> updateById(Parada parada, Long id) {
		ParadaResponseRest response = new ParadaResponseRest();
		List <Parada> list = new ArrayList<>();
		try {
			Optional<Parada> paradaSearch = paradaRepository.findById(id);
			if (paradaSearch.isPresent()) {
				paradaSearch.get().setUbicacion(parada.getUbicacion());
				Parada paradaToUpdate = paradaRepository.save(paradaSearch.get());
				if (paradaToUpdate != null) {
					list.add(paradaToUpdate);
					response.getParadaResponse().setParada(list);
					response.setMetadaData("ok", "00", "Parada actualizada");		
				}				
				else {
					response.setMetadaData("nok", "-1", "Parada no actualizada");
					return new ResponseEntity<ParadaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}
			else {
				response.setMetadaData("nok", "-1", "Parada no encontrada");
				return new ResponseEntity<ParadaResponseRest>(response, HttpStatus.NOT_FOUND);
			}			
		}		
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al actualizar parada");
			e.getStackTrace();
			
			}		
		return new ResponseEntity<ParadaResponseRest>(response, HttpStatus.OK);
	}


	@Transactional
	public ResponseEntity<ParadaResponseRest> deleteById(Long id) {
		ParadaResponseRest response = new ParadaResponseRest();
		try {
			paradaRepository.deleteById(id);
			response.setMetadaData("Respuesta ok", "00", "Registro eliminado");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<ParadaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<ParadaResponseRest>(response, HttpStatus.OK);
	}
	





}
