package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mifse.backend.excepciones.TipoTrabajoFinResidenciaNotFoundException;
import com.mifse.backend.servicios.ServicioTipoTrabajoFinResidencia;

@RestController
@RequestMapping(path = "/tipos-trabajos")
public class ControladorTipoTrabajoFinResidencia {

	@Autowired
	private ServicioTipoTrabajoFinResidencia servicioTipoTrabajoFinResidencia;

	@GetMapping
	private ResponseEntity<?> obtenerTiposTrabajosFinResidencia() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.servicioTipoTrabajoFinResidencia.obtenerTodos());
		} catch (TipoTrabajoFinResidenciaNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
