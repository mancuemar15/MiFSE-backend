package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.servicios.ServicioTipoTrabajoFinResidencia;

@RestController
@RequestMapping(path = "/tipos-trabajos-fin-residencia")
public class ControladorTipoTrabajoFinResidencia {

	@Autowired
	private ServicioTipoTrabajoFinResidencia servicioTipoTrabajoFinResidencia;

	@GetMapping
	private ResponseEntity<?> obtenerTiposTrabajosFinResidencia() {
		return ResponseEntity.ok(this.servicioTipoTrabajoFinResidencia.obtenerTodos());
	}

}
