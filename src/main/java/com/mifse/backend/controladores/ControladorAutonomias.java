package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.servicios.ServicioAutonomia;

@RestController
@RequestMapping(path = "/autonomias")
public class ControladorAutonomias {

	@Autowired
	private ServicioAutonomia servicioAutonomia;

	@GetMapping
	public ResponseEntity<?> obtenerAutonomias() {
		return ResponseEntity.ok(this.servicioAutonomia.obtenerTodas());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerAutonomiaPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(this.servicioAutonomia.obtenerPorId(id));
	}
}
