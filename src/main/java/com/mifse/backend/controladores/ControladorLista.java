package com.mifse.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.excepciones.ActualizacionListaException;
import com.mifse.backend.excepciones.CreacionListaException;
import com.mifse.backend.excepciones.EliminacionListaException;
import com.mifse.backend.excepciones.ListaNotFoundException;
import com.mifse.backend.persistencia.modelos.Lista;
import com.mifse.backend.servicios.ServicioLista;
import com.mifse.backend.vistas.Vistas;

@RestController
@RequestMapping("/listas")
public class ControladorLista {

	@Autowired
	private ServicioLista servicioLista;

	@JsonView(Vistas.ListaExtendida.class)
	@GetMapping("/{id}")
	public ResponseEntity<Lista> obtenerListaPorId(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(this.servicioLista.obtenerPorIdOrdenadoPorNumeroPreferencia(id));
		} catch (ListaNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
	}

	@JsonView(Vistas.ListaPreferencias.class)
	@GetMapping("/{id}/preferencias")
	public ResponseEntity<Lista> obtenerListaPreferenciasPorId(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(this.servicioLista.obtenerPorIdOrdenadoPorNumeroPreferencia(id));
		} catch (ListaNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("authentication.principal.id == #idResidente")
	@JsonView(Vistas.ListaPreferencias.class)
	@GetMapping("/residente/{idResidente}")
	public ResponseEntity<List<Lista>> obtenerListasDeResidente(@PathVariable Long idResidente) {
		try {
			return ResponseEntity.ok(this.servicioLista.obtenerListasPorIdResidente(idResidente));
		} catch (ListaNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("authentication.principal.id == #lista.residente.id")
	@JsonView(Vistas.ListaPreferencias.class)
	@PostMapping
	public ResponseEntity<Lista> crearLista(@RequestBody Lista lista) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.servicioLista.crear(lista));
		} catch (CreacionListaException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("authentication.principal.id == #lista.residente.id")
	@JsonView(Vistas.ListaPreferencias.class)
	@PutMapping
	public ResponseEntity<Lista> actualizarLista(@RequestBody Lista lista) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.servicioLista.actualizar(lista));
		} catch (ActualizacionListaException | ListaNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarLista(@PathVariable Long id) {
		try {
			this.servicioLista.eliminarPorId(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (EliminacionListaException | ListaNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
