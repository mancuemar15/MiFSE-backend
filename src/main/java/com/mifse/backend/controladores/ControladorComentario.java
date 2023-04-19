package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.Comentario;
import com.mifse.backend.servicios.ServicioComentario;

@RestController
@RequestMapping("/comentarios")
public class ControladorComentario {

	@Autowired
	private ServicioComentario servicioComentario;

	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Comentario comentario) {
		Comentario comentarioGuardado = this.servicioComentario.guardar(comentario);
		return ResponseEntity.status(HttpStatus.CREATED).body(comentarioGuardado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrar(@PathVariable Integer id) {
		this.servicioComentario.eliminarPorId(id);
		return ResponseEntity.noContent().build();
	}
}
