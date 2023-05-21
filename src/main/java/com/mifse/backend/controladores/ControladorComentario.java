package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.excepciones.ComentarioNotFoundException;
import com.mifse.backend.excepciones.EliminacionComentarioException;
import com.mifse.backend.excepciones.GuardarComentarioException;
import com.mifse.backend.persistencia.modelos.Comentario;
import com.mifse.backend.servicios.ServicioComentario;
import com.mifse.backend.vistas.Vistas;

@RestController
@RequestMapping("/comentarios")
public class ControladorComentario {

	@Autowired
	private ServicioComentario servicioComentario;

	@PreAuthorize("authentication.principal.id == #comentario.residente.id")
	@JsonView(Vistas.Comentario.class)
	@PostMapping
	public ResponseEntity<Comentario> guardar(@RequestBody Comentario comentario) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.servicioComentario.guardar(comentario));
		} catch (GuardarComentarioException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Comentario> borrar(@PathVariable Long id) {
		try {
			this.servicioComentario.eliminarPorId(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (EliminacionComentarioException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ComentarioNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
}
