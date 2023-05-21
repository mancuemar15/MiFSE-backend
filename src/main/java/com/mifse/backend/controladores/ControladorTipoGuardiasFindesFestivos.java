package com.mifse.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mifse.backend.excepciones.TipoGuardiasFindesFestivosNotFoundException;
import com.mifse.backend.persistencia.modelos.TipoGuardiasFindesFestivos;
import com.mifse.backend.servicios.ServicioTipoGuardiasFindesFestivos;

@RestController
@RequestMapping(path = "/tipos-guardias")
public class ControladorTipoGuardiasFindesFestivos {

	@Autowired
	private ServicioTipoGuardiasFindesFestivos servicioTipoGuardiasFindesFestivos;

	@GetMapping
	private ResponseEntity<List<TipoGuardiasFindesFestivos>> obtenerEpocasVacaciones() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.servicioTipoGuardiasFindesFestivos.obtenerTodos());
		} catch (TipoGuardiasFindesFestivosNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
}
