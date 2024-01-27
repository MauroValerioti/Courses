package com.example.microAdministracion.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MantenimientoResponseRest extends ResponseRest {
    
    private MantenimientoResponse mantenimientoResponse = new MantenimientoResponse();

    public MantenimientoResponse getMantenimientoResponse() {
        return this.mantenimientoResponse;
    }
}
