package com.mifse.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.excepciones.CentroNotFoundException;
import com.mifse.backend.persistencia.modelos.Centro;
import com.mifse.backend.servicios.ServicioCentro;
import com.mifse.backend.vistas.Vistas;

@RestController
@RequestMapping("/centros")
public class ControladorCentro {

	@Autowired
	private ServicioCentro servicioCentro;

	@JsonView(Vistas.Centro.class)
	@GetMapping("/{id}")
	public ResponseEntity<Centro> obtenerCentroPorId(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.servicioCentro.obtenerPorId(id));
		} catch (CentroNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@JsonView(Vistas.CentroReducido.class)
	@GetMapping("/buscar/{nombre}")
	public ResponseEntity<List<Centro>> obtenerCentrosPorNombre(@PathVariable String nombre) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.servicioCentro.obtenerPorNombre(nombre));
		} catch (CentroNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
}
