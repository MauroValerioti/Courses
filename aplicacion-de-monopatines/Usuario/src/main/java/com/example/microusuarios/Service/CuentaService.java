package com.example.microusuarios.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.microusuarios.model.Cuenta;
import com.example.microusuarios.repository.CuentaRepository;
import com.example.microusuarios.response.CuentaResponseRest;

@Service
public class CuentaService {
	
	@Autowired
	CuentaRepository cuentaRepository;
	
	@Transactional(readOnly=true)
	public ResponseEntity<CuentaResponseRest> getAll() {
		
		CuentaResponseRest response = new CuentaResponseRest();
		try {
			List<Cuenta> cuenta = (List<Cuenta>)cuentaRepository.findAll();
			response.getCuentaResponse().setCuenta(cuenta);
			response.setMetadaData("Respuesta ok", "00", "Respuesta exitosa");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<CuentaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<CuentaResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional(readOnly=true)
	public ResponseEntity<CuentaResponseRest> getById(Long id) {
		
		CuentaResponseRest response = new CuentaResponseRest();
		List <Cuenta> list = new ArrayList<>();
		try {
			Optional<Cuenta> Cuenta = cuentaRepository.findById(id);
			if (Cuenta.isPresent()) {
				list.add(Cuenta.get());
				response.getCuentaResponse().setCuenta(list);
				response.setMetadaData("ok", "00", "Cuenta encontrado");
			}
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<CuentaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<CuentaResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Transactional
	public ResponseEntity<CuentaResponseRest> save(Cuenta Cuenta) {
		CuentaResponseRest response = new CuentaResponseRest();
		List <Cuenta> list = new ArrayList<>();
		
		try {
			Cuenta CuentaSaved = cuentaRepository.save(Cuenta);
			if (CuentaSaved != null) {
				list.add(CuentaSaved);
				response.getCuentaResponse().setCuenta(list);
				response.setMetadaData("ok", "00", "Cuenta guardado");
			}
			else {
				response.setMetadaData("nok", "-1", "Cuenta no guardado");
				return new ResponseEntity<CuentaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		}
		
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al grabar Cuenta");
			e.getStackTrace();
			
			}
		
		return new ResponseEntity<CuentaResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<CuentaResponseRest> updateById(Cuenta Cuenta, Long id) {
		CuentaResponseRest response = new CuentaResponseRest();
		List <Cuenta> list = new ArrayList<>();
		try {
			Optional<Cuenta> CuentaSearch = cuentaRepository.findById(id);
			if (CuentaSearch.isPresent()) {
				CuentaSearch.get().setSaldo(Cuenta.getSaldo());
				CuentaSearch.get().setActiva(Cuenta.isActiva());
				Cuenta CuentaToUpdate = cuentaRepository.save(CuentaSearch.get());
				if (CuentaToUpdate != null) {
					list.add(CuentaToUpdate);
					response.getCuentaResponse().setCuenta(list);
					response.setMetadaData("ok", "00", "Cuenta actualizado");		
				}				
				else {
					response.setMetadaData("nok", "-1", "Cuenta no actualizado");
					return new ResponseEntity<CuentaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}
			else {
				response.setMetadaData("nok", "-1", "Cuenta no encontrado");
				return new ResponseEntity<CuentaResponseRest>(response, HttpStatus.NOT_FOUND);
			}			
		}		
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al actualizar Cuenta");
			e.getStackTrace();
			
			}		
		return new ResponseEntity<CuentaResponseRest>(response, HttpStatus.OK);
	}


	@Transactional
	public ResponseEntity<CuentaResponseRest> deleteById(Long id) {
		CuentaResponseRest response = new CuentaResponseRest();
		try {
			cuentaRepository.deleteById(id);
			response.setMetadaData("Respuesta ok", "00", "Registro eliminado");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<CuentaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<CuentaResponseRest>(response, HttpStatus.OK);
	}

	public ResponseEntity<CuentaResponseRest> desactivarCuenta(Long id) {
		CuentaResponseRest response = new CuentaResponseRest();
		List <Cuenta> list = new ArrayList<>();
		try {
			Optional<Cuenta> CuentaSearch = cuentaRepository.findById(id);
			if (CuentaSearch.isPresent()) {
				CuentaSearch.get().setActiva(false);
				Cuenta CuentaToUpdate = cuentaRepository.save(CuentaSearch.get());
				if (CuentaToUpdate != null) {
					list.add(CuentaToUpdate);
					response.getCuentaResponse().setCuenta(list);
					response.setMetadaData("ok", "00", "Cuenta desactivada");		
				}				
				else {
					response.setMetadaData("nok", "-1", "Cuenta no desactivada");
					return new ResponseEntity<CuentaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<CuentaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<CuentaResponseRest>(response, HttpStatus.OK);
	
	}



}

