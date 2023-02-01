package com.apiusuariobasica.controller.usuario.response;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class UsuarioResponse {

	private Long id;
	
	private String nome;
	
	private String email;
	
	private String senha;
}
