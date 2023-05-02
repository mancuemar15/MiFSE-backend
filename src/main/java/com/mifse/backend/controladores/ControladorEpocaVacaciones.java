package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.servicios.ServicioEpocaVacaciones;

@RestController
@RequestMapping(path = "/epocas-vacaciones")
public class ControladorEpocaVacaciones {

	@Autowired
	private ServicioEpocaVacaciones servicioEpocaVacaciones;

	@GetMapping
	private ResponseEntity<?> obtenerEpocasVacaciones() {
		return ResponseEntity.ok(this.servicioEpocaVacaciones.obtenerTodas());
	}

}
