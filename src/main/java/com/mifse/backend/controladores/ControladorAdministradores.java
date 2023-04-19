package com.mifse.backend.controladores;

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

import com.mifse.backend.persistencia.modelos.Administrador;
import com.mifse.backend.servicios.ServicioAdministrador;

@RestController
@RequestMapping("/administradores")
public class ControladorAdministradores {

	@Autowired
	private ServicioAdministrador servicioAdministrador;

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerAdministradorPorId(@PathVariable Integer id) {
		Administrador administrador = this.servicioAdministrador.obtenerPorId(id);
		if (administrador != null) {
			return ResponseEntity.ok(administrador);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Administrador> guardarAdministrador(@RequestBody Administrador administrador) {
		Administrador nuevoAdministrador = this.servicioAdministrador.guardar(administrador);
		return new ResponseEntity<>(nuevoAdministrador, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarAdministrador(@PathVariable Integer id, @RequestBody Administrador administrador) {
		Administrador administradorExistente = this.servicioAdministrador.obtenerPorId(id);
		if (administradorExistente != null) {
			administrador.setId(id);
			Administrador administradorActualizado = this.servicioAdministrador.guardar(administrador);
			return ResponseEntity.ok(administradorActualizado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarAdministrador(@PathVariable Integer id) {
		Administrador administradorExistente = this.servicioAdministrador.obtenerPorId(id);
		if (administradorExistente != null) {
			this.servicioAdministrador.eliminarPorId(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}