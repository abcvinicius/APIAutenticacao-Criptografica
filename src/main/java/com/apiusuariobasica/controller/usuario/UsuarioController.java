package com.apiusuariobasica.controller.usuario;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.apiusuariobasica.controller.usuario.request.UsuarioRequest;
import com.apiusuariobasica.controller.usuario.response.UsuarioResponse;
import com.apiusuariobasica.repository.usuario.UsuarioRepository;
import com.apiusuariobasica.service.usuario.UsuarioService;


@RequestMapping
@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/register")                                                                                          
	public ResponseEntity<UsuarioResponse> cadastroUsuario(@RequestBody @Validated UsuarioRequest usuarioRequest,     
			UriComponentsBuilder UriBuilder) throws Exception {      
		
		
		usuarioRequest.setSenha(encoder.encode(usuarioRequest.getSenha()));
			UsuarioResponse usuario = usuarioService.save(usuarioRequest);													
				
			URI uri = UriBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();														
			return ResponseEntity.created(uri).body(usuario);																	
		
	}
	
	@GetMapping("/buscarusuario")
	public List<UsuarioResponse> listar() { 	
		return usuarioService.search();
	}
	
	@GetMapping("/buscarusuario/{id}")
	public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
	UsuarioResponse usuario = usuarioService.getById(id);
	if(usuario == null) {
	return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok(usuario);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Boolean> validacao(@RequestParam String email, 
												@RequestParam String senha){
	Optional<UsuarioResponse> optUsuarioResponse = usuarioRepository.findByEmail(email);
	if(optUsuarioResponse.isEmpty()) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
	}
	
	
	UsuarioResponse validacaoSenhaUsuarioResponse = optUsuarioResponse.get();
	boolean valid =	encoder.matches(senha, validacaoSenhaUsuarioResponse.getSenha());
	
	HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
	return ResponseEntity.status(status).body(valid);

	}
}
