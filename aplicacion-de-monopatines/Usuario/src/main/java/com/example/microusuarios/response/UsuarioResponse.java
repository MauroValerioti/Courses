package com.example.microusuarios.response;

import java.util.List;

import com.example.microusuarios.model.Usuario;

import lombok.Data;
@Data
public class UsuarioResponse {
	private List<Usuario> usuario; 
}
