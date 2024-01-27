package com.example.microAdministracion.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;

import com.example.microAdministracion.model.Mantenimiento;
import com.example.microAdministracion.repository.MantenimientoRepository;
import com.example.microAdministracion.response.MantenimientoResponseRest;

@Service
public class MantenimientoService {
	
	@Autowired
	MantenimientoRepository mantenimientoRepository;
	
	
	
	@Transactional(readOnly=true)
	public ResponseEntity<MantenimientoResponseRest> getAll() {
		
		MantenimientoResponseRest response = new MantenimientoResponseRest();
		try {
			List<Mantenimiento> mantenimiento = (List<Mantenimiento>)mantenimientoRepository.findAll();
			response.getMantenimientoResponse().setMantenimiento(mantenimiento);
			response.setMetadaData("Respuesta ok", "00", "Respuesta exitosa");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<MantenimientoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<MantenimientoResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional(readOnly=true)
	public ResponseEntity<MantenimientoResponseRest> getById(Long id) {
		
		MantenimientoResponseRest response = new MantenimientoResponseRest();
		List <Mantenimiento> list = new ArrayList<>();
		try {
			Optional<Mantenimiento> mantenimiento = mantenimientoRepository.findById(id);
			if (mantenimiento.isPresent()) {
				list.add(mantenimiento.get());
				response.getMantenimientoResponse().setMantenimiento(list);
				response.setMetadaData("ok", "00", "Mantenimiento encontrada");
			}
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<MantenimientoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<MantenimientoResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Transactional
	public ResponseEntity<MantenimientoResponseRest> save(Mantenimiento mantenimiento) {
		MantenimientoResponseRest response = new MantenimientoResponseRest();
		List <Mantenimiento> list = new ArrayList<>();
		
		try {
			Mantenimiento mantenimientoSaved = mantenimientoRepository.save(mantenimiento);
			if (mantenimientoSaved != null) {
				list.add(mantenimientoSaved);
				response.getMantenimientoResponse().setMantenimiento(list);
				response.setMetadaData("ok", "00", "Mantenimiento guardada");
			}
			else {
				response.setMetadaData("nok", "-1", "Mantenimiento no guardada");
				return new ResponseEntity<MantenimientoResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		}
		
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al grabar mantenimiento");
			e.getStackTrace();
			
			}
		
		return new ResponseEntity<MantenimientoResponseRest>(response, HttpStatus.OK);
	}
	
//	@Transactional
//	public ResponseEntity<MantenimientoResponseRest> updateById(Mantenimiento mantenimiento, Long id) {
//		MantenimientoResponseRest response = new MantenimientoResponseRest();
//		List <Mantenimiento> list = new ArrayList<>();
//		try {
//			Optional<Mantenimiento> mantenimientoSearch = mantenimientoRepository.findById(id);
//			if (mantenimientoSearch.isPresent()) {
//				mantenimientoSearch.get().setUbicacion(mantenimiento.getUbicacion());
//				Mantenimiento mantenimientoToUpdate = mantenimientoRepository.save(mantenimientoSearch.get());
//				if (mantenimientoToUpdate != null) {
//					list.add(mantenimientoToUpdate);
//					response.getMantenimientoResponse().setMantenimiento(list);
//					response.setMetadaData("ok", "00", "Mantenimiento actualizado");		
//				}				
//				else {
//					response.setMetadaData("nok", "-1", "Mantenimiento no actualizada");
//					return new ResponseEntity<MantenimientoResponseRest>(response, HttpStatus.BAD_REQUEST);
//				}
//			}
//			else {
//				response.setMetadaData("nok", "-1", "Mantenimiento no encontrado");
//				return new ResponseEntity<MantenimientoResponseRest>(response, HttpStatus.NOT_FOUND);
//			}			
//		}		
//		catch (Exception e) {
//			response.setMetadaData("Respuesta error", "-1", "Error al actualizar mantenimiento");
//			e.getStackTrace();
//			
//			}		
//		return new ResponseEntity<MantenimientoResponseRest>(response, HttpStatus.OK);
//	}


	@Transactional
	public ResponseEntity<MantenimientoResponseRest> deleteById(Long id) {
		MantenimientoResponseRest response = new MantenimientoResponseRest();
		try {
			mantenimientoRepository.deleteById(id);
			response.setMetadaData("Respuesta ok", "00", "Registro eliminado");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<MantenimientoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<MantenimientoResponseRest>(response, HttpStatus.OK);
	}
	





}
