package com.apiusuariobasica.service.usuario;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiusuariobasica.controller.usuario.request.UsuarioRequest;
import com.apiusuariobasica.controller.usuario.response.UsuarioResponse;
import com.apiusuariobasica.repository.usuario.UsuarioRepository;
import com.apiusuariobasica.utils.usuario.UsuarioBuilder;

@Service
public class UsuarioService {

	@Autowired	
	UsuarioBuilder usuarioBuilder;
	
	@Autowired
	
	UsuarioRepository usuarioRepository;
	
	
	//Method Save
	public UsuarioResponse save(UsuarioRequest usuarioRequest) {
		return usuarioBuilder.buildUsuarioResponse(usuarioRepository.save(usuarioBuilder.buildUsuario(usuarioRequest)));
	}
	
	//Method Search
	public List<UsuarioResponse> search(){
		return usuarioBuilder.buildUsuarioResponse(usuarioRepository.findAll());
	}
	
	
	/* DAR UMA AJEITADA NO FUNÇÃO  orElse, CRIAR UMA EXCEÇÃO PARA ELA SER TRATADA */
	public UsuarioResponse getById(Long id) {
	    return usuarioBuilder.buildUsuarioResponse(usuarioRepository.findById(id).orElse(null));
	}
	

	   
}
