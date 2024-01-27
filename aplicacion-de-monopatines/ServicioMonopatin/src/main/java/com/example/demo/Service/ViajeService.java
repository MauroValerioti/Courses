package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Monopatin;
import com.example.demo.model.Viaje;
import com.example.demo.response.ViajeResponseRest;
import com.example.demo.repository.MonopatinRepository;
import com.example.demo.repository.ViajeRepository;

@Service
public class ViajeService {
	
	@Autowired
	ViajeRepository viajeRepository;
	@Autowired
	MonopatinRepository monopatinRepository;
	
	
	@Transactional(readOnly=true)
	public ResponseEntity<ViajeResponseRest> getAll() {
		
		ViajeResponseRest response = new ViajeResponseRest();
		try {
			List<Viaje> viaje = (List<Viaje>)viajeRepository.findAll();
			response.getViajeResponse().setViaje(viaje);
			response.setMetadaData("Respuesta ok", "00", "Respuesta exitosa");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<ViajeResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<ViajeResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional(readOnly=true)
	public ResponseEntity<ViajeResponseRest> getById(Long id) {
		
		ViajeResponseRest response = new ViajeResponseRest();
		List <Viaje> list = new ArrayList<>();
		try {
			Optional<Viaje> viaje = viajeRepository.findById(id);
			if (viaje.isPresent()) {
				list.add(viaje.get());
				response.getViajeResponse().setViaje(list);
				response.setMetadaData("ok", "00", "Viaje encontrado");
			}
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<ViajeResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<ViajeResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Transactional
	public ResponseEntity<ViajeResponseRest> save(Viaje viaje) {
		ViajeResponseRest response = new ViajeResponseRest();
		List <Viaje> list = new ArrayList<>();
		
		try {
			Optional <Monopatin> monopatinSearch = monopatinRepository.findById(viaje.getIdMonopatin());
			if (monopatinSearch.isPresent()) {
				Viaje viajeSaved = viajeRepository.save(viaje);
				Monopatin monopatin = monopatinSearch.get();
				monopatin.getViajes().add(viajeSaved);
				if (viajeSaved != null) {
					list.add(viajeSaved);
					response.getViajeResponse().setViaje(list);
					response.setMetadaData("ok", "00", "Viaje guardado");
				}
				else {
					response.setMetadaData("nok", "-1", "Viaje no guardado");
					return new ResponseEntity<ViajeResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
		}
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al grabar viaje");
			e.getStackTrace();
			
			}
		
		return new ResponseEntity<ViajeResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<ViajeResponseRest> updateById(Viaje viaje, Long id) {
		ViajeResponseRest response = new ViajeResponseRest();
		List <Viaje> list = new ArrayList<>();
		try {
			Optional<Viaje> viajeSearch = viajeRepository.findById(id);
			if (viajeSearch.isPresent()) {
				viajeSearch.get().setFechaYHoraInicio(viaje.getFechaYHoraInicio());
				viajeSearch.get().setFechaYHoraFin(viaje.getFechaYHoraFin());
				viajeSearch.get().setFechaYHoraInicioPausa(viaje.getFechaYHoraInicioPausa());
				viajeSearch.get().setFechaYHoraFinPausa(viaje.getFechaYHoraFinPausa());
				/*viajeSearch.get().setTarifaNormal(viaje.getTarifaNormal());
				viajeSearch.get().setTarifaDiferencial(viaje.getTarifaDiferencial());*/
				/*viajeSearch.get().setMonopatin(viaje.getMonopatin());*/
				viajeSearch.get().setKilometros(viaje.getKilometros());
				Viaje viajeToUpdate = viajeRepository.save(viajeSearch.get());
				if (viajeToUpdate != null) {
					list.add(viajeToUpdate);
					response.getViajeResponse().setViaje(list);
					response.setMetadaData("ok", "00", "Viaje actualizado");		
				}				
				else {
					response.setMetadaData("nok", "-1", "Viaje no actualizado");
					return new ResponseEntity<ViajeResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}
			else {
				response.setMetadaData("nok", "-1", "Viaje no encontrado");
				return new ResponseEntity<ViajeResponseRest>(response, HttpStatus.NOT_FOUND);
			}			
		}		
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al actualizar Viaje");
			e.getStackTrace();
			
			}		
		return new ResponseEntity<ViajeResponseRest>(response, HttpStatus.OK);
	}


	@Transactional
	public ResponseEntity<ViajeResponseRest> deleteById(Long id) {
		ViajeResponseRest response = new ViajeResponseRest();
		try {
			viajeRepository.deleteById(id);
			response.setMetadaData("Respuesta ok", "00", "Registro eliminado");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<ViajeResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<ViajeResponseRest>(response, HttpStatus.OK);
	}
	





}
