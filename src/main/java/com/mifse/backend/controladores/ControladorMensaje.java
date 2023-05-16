package com.mifse.backend.controladores;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.persistencia.modelos.Mensaje;
import com.mifse.backend.persistencia.modelos.Usuario;
import com.mifse.backend.servicios.ServicioMensaje;
import com.mifse.backend.vistas.Vistas;

@RestController
@RequestMapping("/mensajes")
public class ControladorMensaje {

	@Autowired
	private ServicioMensaje servicioMensaje;

	@JsonView(Vistas.Conversacion.class)
	@GetMapping("/usuarios/{idUsuario1}/{idUsuario2}")
	public ResponseEntity<?> obtenerMensajesPorUsuarioEmisorYUsuarioReceptor(@PathVariable Integer idUsuario1,
			@PathVariable Integer idUsuario2) {
		List<Mensaje> mensajes = this.servicioMensaje.obtenerTodosPorIdEmisorYIdReceptor(idUsuario1, idUsuario2);
		if (mensajes.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(mensajes);
		}
	}

	@GetMapping("/usuarios/{idUsuario}")
	public ResponseEntity<?> obtenerUsuariosConMensajesIntercambiados(@PathVariable Integer idUsuario) {
		Set<Usuario> usuarios = this.servicioMensaje.obtenerUsuariosConMensajesIntercambiados(idUsuario);
		if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(usuarios);
		}
	}

	@GetMapping("/usuarios/{idUsuario}/sin-leer")
	public ResponseEntity<?> obtenerUsuariosConMensajesIntercambiadosSinLeer(@PathVariable Integer idUsuario) {
		Set<Usuario> usuarios = this.servicioMensaje.obtenerUsuariosConMensajesIntercambiadosSinLeer(idUsuario);
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

	@PutMapping("/leidos")
	public ResponseEntity<?> marcarMensajesComoLeidos(@RequestBody List<Mensaje> mensajes) {
		try {
			this.servicioMensaje.marcarComoLeidos(mensajes);
			return ResponseEntity.status(HttpStatus.OK).body("Mensajes actualizados");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No se han podido actualizar");
		}
	}

}
