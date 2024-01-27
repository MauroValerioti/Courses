package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;


import com.example.demo.model.Tarifa;
import com.example.demo.response.TarifaResponseRest;
import com.example.demo.repository.TarifaRepository;

@Service
public class TarifaService {
	
	@Autowired
	TarifaRepository tarifaRepository;
	
	
	
	@Transactional(readOnly=true)
	public ResponseEntity<TarifaResponseRest> getAll() {
		
		TarifaResponseRest response = new TarifaResponseRest();
		try {
			List<Tarifa> tarifa = (List<Tarifa>)tarifaRepository.findAll();
			response.getTarifaResponse().setTarifa(tarifa);
			response.setMetadaData("Respuesta ok", "00", "Respuesta exitosa");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<TarifaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<TarifaResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional(readOnly=true)
	public ResponseEntity<TarifaResponseRest> getById(Long id) {
		
		TarifaResponseRest response = new TarifaResponseRest();
		List <Tarifa> list = new ArrayList<>();
		try {
			Optional<Tarifa> tarifa = tarifaRepository.findById(id);
			if (tarifa.isPresent()) {
				list.add(tarifa.get());
				response.getTarifaResponse().setTarifa(list);
				response.setMetadaData("ok", "00", "Tarifa encontrada");
			}
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<TarifaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<TarifaResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Transactional
	public ResponseEntity<TarifaResponseRest> save(Tarifa tarifa) {
		TarifaResponseRest response = new TarifaResponseRest();
		List <Tarifa> list = new ArrayList<>();
		
		try {
			Tarifa tarifaSaved = tarifaRepository.save(tarifa);
			if (tarifaSaved != null) {
				list.add(tarifaSaved);
				response.getTarifaResponse().setTarifa(list);
				response.setMetadaData("ok", "00", "Tarifa guardada");
			}
			else {
				response.setMetadaData("nok", "-1", "Tarifa no guardada");
				return new ResponseEntity<TarifaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		}
		
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al grabar Tarifa");
			e.getStackTrace();
			
			}
		
		return new ResponseEntity<TarifaResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<TarifaResponseRest> updateById(Tarifa tarifa, Long id) {
		TarifaResponseRest response = new TarifaResponseRest();
		List <Tarifa> list = new ArrayList<>();
		try {
			Optional<Tarifa> tarifaSearch = tarifaRepository.findById(id);
			if (tarifaSearch.isPresent()) {
				tarifaSearch.get().setTarifaNormal(tarifa.getTarifaNormal());
				tarifaSearch.get().setTarifaDiferencial(tarifa.getTarifaDiferencial());
				tarifaSearch.get().setFechaVigencia(tarifa.getFechaVigencia());
				Tarifa tarifaToUpdate = tarifaRepository.save(tarifaSearch.get());
				if (tarifaToUpdate != null) {
					list.add(tarifaToUpdate);
					response.getTarifaResponse().setTarifa(list);
					response.setMetadaData("ok", "00", "Tarifa actualizada");		
				}				
				else {
					response.setMetadaData("nok", "-1", "Tarifa no actualizada");
					return new ResponseEntity<TarifaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}
			else {
				response.setMetadaData("nok", "-1", "Tarifa no encontrada");
				return new ResponseEntity<TarifaResponseRest>(response, HttpStatus.NOT_FOUND);
			}			
		}		
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al actualizar Tarifa");
			e.getStackTrace();
			
			}		
		return new ResponseEntity<TarifaResponseRest>(response, HttpStatus.OK);
	}


	@Transactional
	public ResponseEntity<TarifaResponseRest> deleteById(Long id) {
		TarifaResponseRest response = new TarifaResponseRest();
		try {
			tarifaRepository.deleteById(id);
			response.setMetadaData("Respuesta ok", "00", "Registro eliminado");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<TarifaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<TarifaResponseRest>(response, HttpStatus.OK);
	}
	





}
