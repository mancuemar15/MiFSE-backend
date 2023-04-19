package com.mifse.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.Centro;
import com.mifse.backend.servicios.ServicioCentro;

@RestController
@RequestMapping("/centros")
public class ControladorCentro {

	@Autowired
	private ServicioCentro servicioCentro;

	@GetMapping("/buscar/{nombre}")
	public ResponseEntity<?> obtenerCentrosPorNombre(@PathVariable String nombre) {
		List<Centro> centros = this.servicioCentro.obtenerPorNombre(nombre);
		if (centros.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(centros);
	}
}
