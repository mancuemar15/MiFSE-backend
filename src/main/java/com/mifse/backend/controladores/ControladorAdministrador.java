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
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.excepciones.AdministradorNotFoundException;
import com.mifse.backend.excepciones.CreacionAdministradorException;
import com.mifse.backend.excepciones.EmailYaExistenteException;
import com.mifse.backend.persistencia.modelos.Administrador;
import com.mifse.backend.servicios.ServicioAdministrador;
import com.mifse.backend.vistas.Vistas;

@RestController
@RequestMapping("/administradores")
public class ControladorAdministrador {

	@Autowired
	private ServicioAdministrador servicioAdministrador;

	@JsonView(Vistas.Administrador.class)
	@PostMapping("/registro")
	public ResponseEntity<Administrador> crearAdministrador(@RequestBody Administrador administrador) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.servicioAdministrador.crear(administrador));
		} catch (EmailYaExistenteException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		} catch (CreacionAdministradorException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("authentication.principal.id == #administrador.id")
	@JsonView(Vistas.Administrador.class)
	@PutMapping
	public ResponseEntity<Administrador> actualizarAdministrador(@RequestBody Administrador administrador) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.servicioAdministrador.actualizar(administrador));
		} catch (AdministradorNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}