/*package com.example.microAdministracion.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MicroServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public String llamarOtroMicroservicio() {
        String url = "http://localhost:8080/api/v1/usuario"; // Cambia la URL según tu configuración
        return restTemplate.getForObject(url, String.class);
    }
}*/
