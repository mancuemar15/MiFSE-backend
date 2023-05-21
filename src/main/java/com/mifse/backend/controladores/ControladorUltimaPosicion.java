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

import com.mifse.backend.excepciones.TitulacionNotFoundException;
import com.mifse.backend.persistencia.modelos.UltimaPosicion;
import com.mifse.backend.servicios.ServicioUltimaPosicion;

@RestController
@RequestMapping("/ultimas-posiciones")
public class ControladorUltimaPosicion {

	@Autowired
	private ServicioUltimaPosicion servicioUltimaPosicion;

	@GetMapping("/{nombreTitulacion}")
	public ResponseEntity<List<UltimaPosicion>> obtenerUltimasPosicionesPorNombreTitulacion(
			@PathVariable String nombreTitulacion) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(this.servicioUltimaPosicion.obtenerTodasPorNombreTitulacion(nombreTitulacion));
		} catch (TitulacionNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
}
