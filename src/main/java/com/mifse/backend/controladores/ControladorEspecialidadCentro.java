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
import com.mifse.backend.excepciones.EspecialidadCentroNotFoundException;
import com.mifse.backend.persistencia.modelos.EspecialidadCentro;
import com.mifse.backend.servicios.ServicioEspecialidadCentro;
import com.mifse.backend.vistas.Vistas;

@RestController
@RequestMapping("/especialidades-centros")
public class ControladorEspecialidadCentro {

	@Autowired
	private ServicioEspecialidadCentro servicioEspecialidadCentro;

	@JsonView(Vistas.Lista.class)
	@GetMapping("/{nombreTitulacion}")
	public ResponseEntity<List<EspecialidadCentro>> obtenerTodasEspecialidadesCentrosPorNombreTitulacion(
			@PathVariable String nombreTitulacion) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(this.servicioEspecialidadCentro.obtenerTodasPorNombreTitulacion(nombreTitulacion));
		} catch (EspecialidadCentroNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
