package com.apiusuariobasica.repository.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiusuariobasica.controller.usuario.response.UsuarioResponse;
import com.apiusuariobasica.model.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	
	public Optional<UsuarioResponse> findByEmail(String email);

}
