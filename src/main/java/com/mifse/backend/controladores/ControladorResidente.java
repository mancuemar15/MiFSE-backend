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

import com.mifse.backend.excepciones.ActualizacionResidenteException;
import com.mifse.backend.excepciones.CreacionResidenteException;
import com.mifse.backend.excepciones.EmailYaExistenteException;
import com.mifse.backend.excepciones.ResidenteNotFoundException;
import com.mifse.backend.persistencia.modelos.Residente;
import com.mifse.backend.servicios.ServicioResidente;

@RestController
@RequestMapping("/residentes")
public class ControladorResidente {

	@Autowired
	private ServicioResidente servicioResidente;

	@PostMapping("/registro")
	public ResponseEntity<Residente> crearResidente(@RequestBody Residente residente) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.servicioResidente.crear(residente));
		} catch (EmailYaExistenteException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		} catch (CreacionResidenteException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("authentication.principal.id == #residente.id")
	@PutMapping
	public ResponseEntity<Residente> actualizarResidente(@RequestBody Residente residente) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.servicioResidente.actualizar(residente));
		} catch (ResidenteNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (ActualizacionResidenteException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}