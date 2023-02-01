package com.apiusuariobasica.utils.usuario;

import java.util.List;

import org.springframework.stereotype.Component;

import com.apiusuariobasica.controller.usuario.request.UsuarioRequest;
import com.apiusuariobasica.controller.usuario.response.UsuarioResponse;
import com.apiusuariobasica.model.usuario.Usuario;

@Component
public class UsuarioBuilder {


	//List
	public List<UsuarioResponse> buildUsuarioResponse(List<Usuario> usuario){
		return usuario.stream().map(usuarios -> buildUsuarioResponse(usuarios)).toList();
	}
	
	
	//Response
	public UsuarioResponse buildUsuarioResponse(Usuario usuario) {
		return UsuarioResponse.builder().id(usuario.getId()).nome(usuario.getNome()).email(usuario.getEmail()).senha(usuario.getSenha()).build();
	}
	
	//Request
	public Usuario buildUsuario(UsuarioRequest usuarioRequest) {
		return Usuario.builder().nome(usuarioRequest.getNome()).email(usuarioRequest.getEmail()).senha(usuarioRequest.getSenha()).build();
	}
}
