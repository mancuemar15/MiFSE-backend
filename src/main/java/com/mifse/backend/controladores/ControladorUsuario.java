package com.mifse.backend.controladores;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@PostMapping(path = "/login")
	public ResponseEntity<?> obtenerUsuarioPorEmailYContrasena(@RequestBody Map<String, String> credenciales) {
		String email = credenciales.get("email");
		String contrasena = credenciales.get("contrasena");

		Usuario usuario = this.servicioUsuario.obtenerPorEmailYContrasena(email, contrasena);

		if (Objects.isNull(usuario)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		return ResponseEntity.ok(usuario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarUsuario(@PathVariable("id") Integer id,
			@RequestBody Usuario usuarioActualizado) {
		try {
			Usuario usuarioExistente = this.servicioUsuario.obtenerPorId(id);

			if (usuarioExistente == null) {
				return ResponseEntity.notFound().build();
			}
			usuarioExistente.setNombre(usuarioActualizado.getNombre());
			usuarioExistente.setApellido1(usuarioActualizado.getApellido1());
			usuarioExistente.setApellido2(usuarioActualizado.getApellido2());
			usuarioExistente.setEmail(usuarioActualizado.getEmail());
			usuarioExistente.setContrasena(usuarioActualizado.getContrasena());
			usuarioExistente.setTipoUsuario(usuarioActualizado.getTipoUsuario());
			this.servicioUsuario.actualizar(usuarioExistente);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarPorId(@PathVariable("id") Integer id) {
		try {
			this.servicioUsuario.eliminarPorId(id);
			return ResponseEntity.ok("Eliminado exitosamente");
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
