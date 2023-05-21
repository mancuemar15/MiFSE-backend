package com.mifse.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mifse.backend.excepciones.TitulacionNotFoundException;
import com.mifse.backend.persistencia.modelos.Titulacion;
import com.mifse.backend.servicios.ServicioTitulacion;

@RestController
@RequestMapping("/titulaciones")
public class ControladorTitulacion {

	@Autowired
	private ServicioTitulacion servicioTitulacion;

	@GetMapping
	public ResponseEntity<List<Titulacion>> obtenerTitulaciones() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.servicioTitulacion.obtenerTodas());
		} catch (TitulacionNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
