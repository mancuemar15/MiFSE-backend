package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.servicios.ServicioTipoResidente;

@RestController
@RequestMapping(path = "/tipos-residentes")
public class ControladorTipoResidente {

	@Autowired
	private ServicioTipoResidente servicioTipoResidente;

	@GetMapping
	private ResponseEntity<?> obtenerTiposResidentes() {
		return ResponseEntity.ok(this.servicioTipoResidente.obtenerTodos());
	}

}
