package com.mifse.backend.controladores;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.excepciones.ActualizacionMensajeException;
import com.mifse.backend.excepciones.MensajeNotFoundException;
import com.mifse.backend.persistencia.modelos.Mensaje;
import com.mifse.backend.persistencia.modelos.Usuario;
import com.mifse.backend.servicios.ServicioMensaje;
import com.mifse.backend.vistas.Vistas;

@RestController
@RequestMapping("/mensajes")
public class ControladorMensaje {

	@Autowired
	private ServicioMensaje servicioMensaje;

	@PreAuthorize("authentication.principal.id == #idUsuario1 or authentication.principal.id == #idUsuario2")
	@JsonView(Vistas.Conversacion.class)
	@GetMapping("/usuarios/{idUsuario1}/{idUsuario2}")
	public ResponseEntity<List<Mensaje>> obtenerMensajesPorUsuarioEmisorYUsuarioReceptor(@PathVariable Long idUsuario1,
			@PathVariable Long idUsuario2) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(this.servicioMensaje.obtenerTodosPorIdEmisorYIdReceptor(idUsuario1, idUsuario2));
		} catch (MensajeNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("authentication.principal.id == #idUsuario")
	@GetMapping("/usuarios/{idUsuario}")
	public ResponseEntity<Set<Usuario>> obtenerUsuariosConMensajesIntercambiados(@PathVariable Long idUsuario) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(this.servicioMensaje.obtenerUsuariosConMensajesIntercambiados(idUsuario));
		} catch (MensajeNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("authentication.principal.id == #idUsuario")
	@GetMapping("/usuarios/{idUsuario}/sin-leer")
	public ResponseEntity<Set<Usuario>> obtenerUsuariosConMensajesIntercambiadosSinLeer(@PathVariable Long idUsuario) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(this.servicioMensaje.obtenerUsuariosConMensajesIntercambiadosSinLeer(idUsuario));
		} catch (MensajeNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("authentication.principal.id == #mensaje.emisor.id and authentication.principal.verificado == true")
	@PostMapping
	public ResponseEntity<Mensaje> guardarMensaje(@RequestBody Mensaje mensaje) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.servicioMensaje.guardar(mensaje));
		} catch (MensajeNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreFilter("filterObject.receptor.id == authentication.principal.id")
	@PutMapping("/leidos")
	public ResponseEntity<?> marcarMensajesComoLeidos(@RequestBody List<Mensaje> mensajes) {
		try {
			this.servicioMensaje.marcarComoLeidos(mensajes);
			return ResponseEntity.status(HttpStatus.OK).body("Mensajes actualizados");
		} catch (ActualizacionMensajeException e) {
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "No se han podido actualizar los mensajes");
		}
	}

}
