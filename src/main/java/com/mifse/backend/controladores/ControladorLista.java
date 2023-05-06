package com.mifse.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@JsonView(Vistas.Lista.class)
	@GetMapping("/residente/{idResidente}")
	public ResponseEntity<?> obtenerListasDeResidente(@PathVariable Integer idResidente) {
		List<Lista> listas = this.servicioLista.obtenerListasPorIdResidente(idResidente);
		if (listas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listas);
	}

	@JsonView(Vistas.Lista.class)
	@PostMapping
	public ResponseEntity<?> crearLista(@RequestBody Lista lista) {
		Lista listaCreada = this.servicioLista.guardar(lista);
		return ResponseEntity.status(HttpStatus.CREATED).body(listaCreada);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarLista(@PathVariable Integer id) {
		this.servicioLista.eliminarPorId(id);
		return ResponseEntity.noContent().build();
	}
}
