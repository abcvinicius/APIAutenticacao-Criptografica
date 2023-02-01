package com.apiusuariobasica.controller.usuario.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioRequest {

	
	private String nome;
	
	
	private String email; 
	
	
	private String senha;
}
