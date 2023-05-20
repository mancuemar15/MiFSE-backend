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

import com.fasterxml.jackson.annotation.JsonView;
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
	public ResponseEntity<?> obtenerListaPorId(@PathVariable Long id) {
		Lista lista = this.servicioLista.obtenerPorIdOrdenadoPorNumeroPreferencia(id);
		if (lista == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lista);
	}

	@JsonView(Vistas.ListaPreferencias.class)
	@GetMapping("/{id}/preferencias")
	public ResponseEntity<?> obtenerListaPreferenciasPorId(@PathVariable Long id) {
		Lista lista = this.servicioLista.obtenerPorIdOrdenadoPorNumeroPreferencia(id);
		if (lista == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lista);
	}

	@PreAuthorize("authentication.principal.id == #idResidente")
	@JsonView(Vistas.ListaPreferencias.class)
	@GetMapping("/residente/{idResidente}")
	public ResponseEntity<?> obtenerListasDeResidente(@PathVariable Long idResidente) {
		List<Lista> listas = this.servicioLista.obtenerListasPorIdResidente(idResidente);
		if (listas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listas);
	}

	@PreAuthorize("authentication.principal.id == #lista.residente.id")
	@JsonView(Vistas.ListaPreferencias.class)
	@PostMapping
	public ResponseEntity<?> crearLista(@RequestBody Lista lista) {
		Lista listaCreada = this.servicioLista.guardar(lista);
		return ResponseEntity.status(HttpStatus.CREATED).body(listaCreada);
	}

	@PreAuthorize("authentication.principal.id == #lista.residente.id")
	@JsonView(Vistas.ListaPreferencias.class)
	@PutMapping
	public ResponseEntity<?> actualizarLista(@RequestBody Lista lista) {
		Lista listaActualizada = this.servicioLista.actualizar(lista);
		return ResponseEntity.status(HttpStatus.OK).body(listaActualizada);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarLista(@PathVariable Long id) {
		this.servicioLista.eliminarPorId(id);
		return ResponseEntity.noContent().build();
	}
}
