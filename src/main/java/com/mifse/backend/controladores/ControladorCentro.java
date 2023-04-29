package com.mifse.backend.controladores;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.Centro;
import com.mifse.backend.persistencia.modelos.dto.CentroDTO;
import com.mifse.backend.servicios.ServicioCentro;

@RestController
@RequestMapping("/centros")
public class ControladorCentro {

	@Autowired
	private ServicioCentro servicioCentro;

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerCentroPorId(@PathVariable Integer id) {
		Centro centro = this.servicioCentro.obtenerPorId(id);
		if (Objects.isNull(centro)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(centro);
	}

	@GetMapping("/buscar/{nombre}")
	public ResponseEntity<?> obtenerCentrosPorNombre(@PathVariable String nombre) {
		List<CentroDTO> centros = this.servicioCentro.obtenerPorNombre(nombre);
		if (centros.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(centros);
	}
}
