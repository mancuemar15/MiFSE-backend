package com.mifse.backend.controladores;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	GeneradorToken generadorToken;

	@Autowired
	DaoAuthenticationProvider daoAuthenticationProvider;

	@JsonView(Vistas.Usuario.class)
	@GetMapping
	public ResponseEntity<?> obtenerTodosUsuarios() {
		return ResponseEntity.ok(this.servicioUsuario.obtenerTodos());
	}

	@PostMapping("/verificar")
	public ResponseEntity<String> verificarCorreoElectronico(@RequestParam("id") Long id) {
		Boolean correoElectronicoVerificado = this.servicioUsuario.verificarCorreoElectronico(id);
		if (correoElectronicoVerificado) {
			return ResponseEntity.ok("Tu correo electrónico ha sido verificado correctamente");
		} else {
			return ResponseEntity.badRequest().body("El ID de verificación no es válido");
		}
	}

	@PostMapping(path = "/login")
	public ResponseEntity<?> iniciarSesionUsuario(@RequestBody Credenciales credenciales) {

		ObjectMapper objectMapper = new ObjectMapper();

		Authentication authentication = daoAuthenticationProvider.authenticate(UsernamePasswordAuthenticationToken
				.unauthenticated(credenciales.getEmail(), credenciales.getContrasena()));

		Usuario usuario = (Usuario) authentication.getPrincipal();

		Map<String, Object> usuarioMap = objectMapper.convertValue(usuario, new TypeReference<Map<String, Object>>() {
		});

		usuarioMap.put("token", this.generadorToken.generarToken(authentication));
		usuarioMap.remove("contrasena");
		usuarioMap.remove("fechaAlta");

		return ResponseEntity.ok(usuarioMap);
	}

	@PutMapping("/bloquear/{id}")
	public ResponseEntity<?> bloquearUsuario(@PathVariable("id") Long id) {
		try {
			Usuario usuario = this.servicioUsuario.bloquear(id);
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario");
		}
	}

	@PreAuthorize("authentication.principal.id == #id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrarUsuario(@PathVariable("id") Long id, @RequestBody Map<String, String> credencial) {
		try {
			this.servicioUsuario.eliminar(id, credencial.get("contrasena"));
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario");
		}
	}
}
