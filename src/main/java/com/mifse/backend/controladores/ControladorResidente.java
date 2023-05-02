package com.mifse.backend.controladores;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.Residente;
import com.mifse.backend.servicios.ServicioResidente;

@RestController
@RequestMapping("/residentes")
public class ControladorResidente {

	@Autowired
	private ServicioResidente servicioResidente;

	@PostMapping
	public ResponseEntity<?> guardarResidente(@RequestBody Residente residente) {
		try {
			return ResponseEntity.ok(this.servicioResidente.guardar(residente));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
