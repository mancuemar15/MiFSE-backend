package com.mifse.backend.controladores;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.Usuario;
import com.mifse.backend.servicios.ServicioUsuario;

@RestController
@RequestMapping(path = "/usuarios")
public class ControladorUsuario {

	@Autowired
	private ServicioUsuario servicioUsuario;

	@GetMapping
	public ResponseEntity<?> obtenerTodosUsuarios() {
		return ResponseEntity.ok(this.servicioUsuario.obtenerTodos());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Integer id) {
		Usuario usuario = this.servicioUsuario.obtenerPorId(id);

		if (Objects.isNull(usuario)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(usuario);
	}

	@GetMapping("/verificar-correo-electronico")
	public ResponseEntity<String> verificarCorreoElectronico(@RequestParam("id") Integer id) {
		Boolean correoElectronicoVerificado = this.servicioUsuario.verificarCorreoElectronico(id);
		if (correoElectronicoVerificado) {
			return ResponseEntity.ok("Tu correo electrónico ha sido verificado correctamente");
		} else {
			return ResponseEntity.badRequest().body("El ID de verificación no es válido");
		}
	}

	@PostMapping(path = "/login")
	public ResponseEntity<?> iniciarSesionUsuario(@RequestBody Map<String, String> credenciales) {
		String email = credenciales.get("email");
		String contrasena = credenciales.get("contrasena");

		Usuario usuario = this.servicioUsuario.obtenerPorEmailYContrasena(email, contrasena);

		if (Objects.isNull(usuario)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else if (usuario.isInhabilitado()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		return ResponseEntity.ok(usuario);
	}

	@PutMapping
	public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario usuarioActualizado) {
		try {
			Usuario usuario = this.servicioUsuario.actualizar(usuarioActualizado);
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario");
		}
	}

	@PutMapping("/bloquear/{id}")
	public ResponseEntity<?> bloquearUsuario(@PathVariable("id") Integer id) {
		try {
			Usuario usuario = this.servicioUsuario.bloquear(id);
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario");
		}
	}
}
