package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.Administrador;
import com.mifse.backend.servicios.ServicioAdministrador;

@RestController
@RequestMapping("/administradores")
public class ControladorAdministrador {

	@Autowired
	private ServicioAdministrador servicioAdministrador;

	@PostMapping("/registro")
	public ResponseEntity<Administrador> guardarAdministrador(@RequestBody Administrador administrador) {
		Administrador nuevoAdministrador = this.servicioAdministrador.guardar(administrador);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAdministrador);
	}

	@PutMapping
	@PreAuthorize("authentication.principal.id == #administrador.id")
	public ResponseEntity<Administrador> actualizarAdministrador(@RequestBody Administrador administrador) {
		Administrador nuevoAdministrador = this.servicioAdministrador.actualizar(administrador);
		return ResponseEntity.status(HttpStatus.OK).body(nuevoAdministrador);
	}

}