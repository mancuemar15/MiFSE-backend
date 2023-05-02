package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.servicios.ServicioTipoGuardiasFindesFestivos;

@RestController
@RequestMapping(path = "/tipos-guardias")
public class ControladorTipoGuardiasFindesFestivos {

	@Autowired
	private ServicioTipoGuardiasFindesFestivos servicioTipoGuardiasFindesFestivos;

	@GetMapping
	private ResponseEntity<?> obtenerEpocasVacaciones() {
		return ResponseEntity.ok(this.servicioTipoGuardiasFindesFestivos.obtenerTodos());
	}
}
