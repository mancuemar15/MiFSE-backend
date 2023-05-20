package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.servicios.ServicioTitulacion;

@RestController
@RequestMapping("/titulaciones")
public class ControladorTitulacion {

	@Autowired
	private ServicioTitulacion servicioTitulacion;

	@GetMapping
	public ResponseEntity<?> obtenerTitulaciones() {
		return ResponseEntity.ok(this.servicioTitulacion.obtenerTodas());
	}

}
