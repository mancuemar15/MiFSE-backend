package com.mifse.backend.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifse.backend.excepciones.BloqueoUsuarioException;
import com.mifse.backend.excepciones.DesbloqueoUsuarioException;
import com.mifse.backend.excepciones.EliminacionUsuarioException;
import com.mifse.backend.excepciones.UsuarioNotFoundException;
import com.mifse.backend.excepciones.VerificacionUsuarioException;
import com.mifse.backend.persistencia.modelos.Usuario;
import com.mifse.backend.persistencia.modelos.dto.Credenciales;
import com.mifse.backend.security.GeneradorToken;
import com.mifse.backend.servicios.ServicioUsuario;
import com.mifse.backend.vistas.Vistas;

@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {

	@Autowired
	private ServicioUsuario servicioUsuario;

	@Autowired
	private GeneradorToken generadorToken;

	@Autowired
	private DaoAuthenticationProvider daoAuthenticationProvider;

	@JsonView(Vistas.Usuario.class)
	@GetMapping
	public ResponseEntity<List<Usuario>> obtenerTodosUsuarios() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.servicioUsuario.obtenerTodos());
		} catch (UsuarioNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/verificar")
	public ResponseEntity<String> verificarCorreoElectronico(@RequestParam Long id) {
		try {
			this.servicioUsuario.verificarUsuario(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (VerificacionUsuarioException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/login")
	public ResponseEntity<?> iniciarSesionUsuario(@RequestBody Credenciales credenciales) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();

			Authentication authentication = this.daoAuthenticationProvider
					.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(credenciales.getEmail(),
							credenciales.getContrasena()));

			Usuario usuario = (Usuario) authentication.getPrincipal();

			Map<String, Object> usuarioMap = objectMapper.convertValue(usuario,
					new TypeReference<Map<String, Object>>() {
					});

			usuarioMap.put("token", this.generadorToken.generarToken(authentication));
			usuarioMap.remove("contrasena");
			usuarioMap.remove("fechaAlta");

			return ResponseEntity.status(HttpStatus.OK).body(usuarioMap);
		} catch (AuthenticationException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/bloquear/{id}")
	public ResponseEntity<?> bloquearUsuario(@PathVariable Long id) {
		try {
			this.servicioUsuario.bloquear(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (BloqueoUsuarioException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/desbloquear/{id}")
	public ResponseEntity<?> desbloquearUsuario(@PathVariable("id") Long id) {
		try {
			this.servicioUsuario.desbloquear(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (DesbloqueoUsuarioException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("authentication.principal.id == #id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrarUsuario(@PathVariable("id") Long id, @RequestBody Map<String, String> credencial) {
		try {
			this.servicioUsuario.eliminar(id, credencial.get("contrasena"));
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (EliminacionUsuarioException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
