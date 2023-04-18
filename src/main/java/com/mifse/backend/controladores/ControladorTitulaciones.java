package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.Titulacion;
import com.mifse.backend.servicios.ServicioTitulacion;

@RestController
@RequestMapping("/titulaciones")
public class ControladorTitulaciones {

	@Autowired
	private ServicioTitulacion servicioTitulacion;

	@GetMapping
	public ResponseEntity<?> obtenerTitulaciones() {
		return ResponseEntity.ok(this.servicioTitulacion.obtenerTodas());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerTitulacionPorId(@PathVariable Integer id) {
		Titulacion titulacion = this.servicioTitulacion.obtenerPorId(id);

		if (titulacion == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(titulacion);
		}
	}
}
