package com.example.microAdministracion.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import com.example.microAdministracion.model.Administrador;
import com.example.microAdministracion.repository.AdministradorRepository;
import com.example.microAdministracion.response.AdministradorResponseRest;
import com.example.microusuarios.model.Usuario;
import com.example.microusuarios.response.UsuarioResponseRest;

@Service
public class AdministradorService {
	
	@Autowired
	AdministradorRepository administradorRepository;
	
	 @Autowired
	 private RestTemplate restTemplate;
	
	
	
	@Transactional(readOnly=true)
	public ResponseEntity<AdministradorResponseRest> getAll() {
		
		AdministradorResponseRest response = new AdministradorResponseRest();
		try {
			List<Administrador> administrador = (List<Administrador>)administradorRepository.findAll();
			response.getAdministradorResponse().setAdministrador(administrador);
			response.setMetadaData("Respuesta ok", "00", "Respuesta exitosa");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<AdministradorResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<AdministradorResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional(readOnly=true)
	public ResponseEntity<AdministradorResponseRest> getById(Long id) {
		
		AdministradorResponseRest response = new AdministradorResponseRest();
		List <Administrador> list = new ArrayList<>();
		try {
			Optional<Administrador> administrador = administradorRepository.findById(id);
			if (administrador.isPresent()) {
				list.add(administrador.get());
				response.getAdministradorResponse().setAdministrador(list);
				response.setMetadaData("ok", "00", "Administrador encontrado");
			}
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<AdministradorResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<AdministradorResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Transactional
	public ResponseEntity<AdministradorResponseRest> save(Administrador administrador) {
		AdministradorResponseRest response = new AdministradorResponseRest();
		List <Administrador> list = new ArrayList<>();
		
		try {
			Administrador administradorSaved = administradorRepository.save(administrador);
			if (administradorSaved != null) {
				list.add(administradorSaved);
				response.getAdministradorResponse().setAdministrador(list);
				response.setMetadaData("ok", "00", "Administrador guardado");
			}
			else {
				response.setMetadaData("nok", "-1", "Administrador no guardado");
				return new ResponseEntity<AdministradorResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		}
		
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al grabar administrador");
			e.getStackTrace();
			
			}
		
		return new ResponseEntity<AdministradorResponseRest>(response, HttpStatus.OK);
	}
	
//	@Transactional
//	public ResponseEntity<AdministradorResponseRest> updateById(Administrador administrador, Long id) {
//		AdministradorResponseRest response = new AdministradorResponseRest();
//		List <Administrador> list = new ArrayList<>();
//		try {
//			Optional<Administrador> administradorSearch = administradorRepository.findById(id);
//			if (administradorSearch.isPresent()) {
//				/*administradorSearch.get().setViajes(administrador.getViajes());*/
//				administradorSearch.get().setUbicacion(administrador.getUbicacion());
//				administradorSearch.get().setEstado(administrador.getEstado());
//				Administrador administradorToUpdate = administradorRepository.save(administradorSearch.get());
//				if (administradorToUpdate != null) {
//					list.add(administradorToUpdate);
//					response.getAdministradorResponse().setAdministrador(list);
//					response.setMetadaData("ok", "00", "Administrador actualizado");		
//				}				
//				else {
//					response.setMetadaData("nok", "-1", "Administrador no actualizado");
//					return new ResponseEntity<AdministradorResponseRest>(response, HttpStatus.BAD_REQUEST);
//				}
//			}
//			else {
//				response.setMetadaData("nok", "-1", "Administrador no encontrado");
//				return new ResponseEntity<AdministradorResponseRest>(response, HttpStatus.NOT_FOUND);
//			}			
//		}		
//		catch (Exception e) {
//			response.setMetadaData("Respuesta error", "-1", "Error al actualizar administrador");
//			e.getStackTrace();
//			
//			}		
//		return new ResponseEntity<AdministradorResponseRest>(response, HttpStatus.OK);
//	}


	@Transactional
	public ResponseEntity<AdministradorResponseRest> deleteById(Long id) {
		AdministradorResponseRest response = new AdministradorResponseRest();
		try {
			administradorRepository.deleteById(id);
			response.setMetadaData("Respuesta ok", "00", "Registro eliminado");
		}
		catch (Exception e) {
			response.setMetadaData("Respuesta error", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<AdministradorResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		return new ResponseEntity<AdministradorResponseRest>(response, HttpStatus.OK);
	}
	
   /* public ResponseEntity<String> crearUsuario(Usuario usuario) {
        String url = "http://localhost:8082/api/v1/usuario"; // Cambia la URL según tu configuración
        return restTemplate.postForEntity(url, usuario, String.class);
    }*/


	@Transactional
	public ResponseEntity<UsuarioResponseRest> anularCuenta(Long idUsuario, Long idCuenta) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    // Puedes crear un objeto para enviar en el cuerpo de la solicitud si es necesario
	    // Ejemplo: RequestBody requestBody = new RequestBody(idUsuario, idCuenta);
	    
	    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

	    ResponseEntity<UsuarioResponseRest> response = restTemplate.postForEntity(
	            "http://localhost:8080/api/v1/usuario/{idUsuario}/desvincularCuenta/{idCuenta}",
	            requestEntity, UsuarioResponseRest.class, idUsuario, idCuenta);

	    return response;
	}
	
	@Transactional
	public ResponseEntity<UsuarioResponseRest> desactivarCuenta(Long idCuenta) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    // Puedes crear un objeto para enviar en el cuerpo de la solicitud si es necesario
	    // Ejemplo: RequestBody requestBody = new RequestBody(idUsuario, idCuenta);
	    
	    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

	    ResponseEntity<UsuarioResponseRest> response = restTemplate.exchange(
	    		"http://localhost:8080/api/v1/desactivarCuenta/{idCuenta}",
	            HttpMethod.PUT,
	            requestEntity,
	            UsuarioResponseRest.class,
	            idCuenta);

	    return response;
	}

	/*@Transactional
	public ResponseEntity<MonopatinResponseRest> requiereMantenimiento() {
		 HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);

		    // Puedes crear un objeto para enviar en el cuerpo de la solicitud si es necesario
		    // Ejemplo: RequestBody requestBody = new RequestBody(idUsuario, idCuenta);
		    
		    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

		    ResponseEntity<MonopatinResponseRest> response = restTemplate.exchange(
		    		"http://localhost:8081/api/v1/mantenimientoMonopatines",
		            HttpMethod.GET,
		            requestEntity,
		            MonopatinResponseRest.class);
		     
		    return response;
	}*/


}
