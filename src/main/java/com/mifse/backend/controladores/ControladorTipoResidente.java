package com.mifse.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mifse.backend.excepciones.TipoResidenteNotFoundException;
import com.mifse.backend.persistencia.modelos.TipoResidente;
import com.mifse.backend.servicios.ServicioTipoResidente;

@RestController
@RequestMapping(path = "/tipos-residentes")
public class ControladorTipoResidente {

	@Autowired
	private ServicioTipoResidente servicioTipoResidente;

	@GetMapping
	private ResponseEntity<List<TipoResidente>> obtenerTiposResidentes() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.servicioTipoResidente.obtenerTodos());
		} catch (TipoResidenteNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
