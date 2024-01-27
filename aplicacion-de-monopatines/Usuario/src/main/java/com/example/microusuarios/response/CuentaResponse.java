package com.example.microusuarios.response;

import java.util.List;

import com.example.microusuarios.model.Cuenta;

import lombok.Data;

@Data
public class CuentaResponse {
	private List<Cuenta> cuenta;

}
