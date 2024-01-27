package com.example.microAdministracion.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministradorResponseRest extends ResponseRest {
	
	private AdministradorResponse administradorResponse = new AdministradorResponse();

	public AdministradorResponse getAdministradorResponse() {
	    return administradorResponse;
	}


}