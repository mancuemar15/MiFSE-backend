package com.mifse.backend.controladores;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.Mensaje;
import com.mifse.backend.persistencia.modelos.Usuario;
import com.mifse.backend.servicios.ServicioMensaje;

@RestController
@RequestMapping("/mensajes")
public class ControladorMensaje {

	@Autowired
	private ServicioMensaje servicioMensaje;

	@GetMapping("/emisor/{idUsuarioEmisor}/receptor/{idUsuarioReceptor}")
	public ResponseEntity<?> obtenerMensajesPorUsuarioEmisorYUsuarioReceptor(@PathVariable Integer idUsuarioEmisor,
			@PathVariable Integer idUsuarioReceptor) {
		List<Mensaje> mensajes = this.servicioMensaje.obtenerTodosPorIdEmisorYIdReceptor(idUsuarioEmisor,
				idUsuarioReceptor);
		if (mensajes.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(mensajes);
		}
	}

	@GetMapping("/usuarios-mensajes-intercambiados/{idUsuario}")
	public ResponseEntity<?> obtenerUsuariosConMensajesIntercambiados(@PathVariable Integer idUsuario) {
		Set<Usuario> usuarios = this.servicioMensaje.obtenerUsuariosConMensajesIntercambiados(idUsuario);
		if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(usuarios);
		}
	}

	@PostMapping
	public ResponseEntity<?> guardarMensaje(@RequestBody Mensaje mensaje) {
		Mensaje mensajeGuardado = this.servicioMensaje.guardar(mensaje);
		return ResponseEntity.status(HttpStatus.CREATED).body(mensajeGuardado);
	}
}
