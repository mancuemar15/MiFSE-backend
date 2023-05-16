package com.mifse.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.persistencia.modelos.EspecialidadCentro;
import com.mifse.backend.servicios.ServicioEspecialidadCentro;
import com.mifse.backend.vistas.Vistas;

@RestController
@RequestMapping("/especialidades-centros")
public class ControladorEspecialidadCentro {

	@Autowired
	private ServicioEspecialidadCentro servicioEspecialidadCentro;

	@GetMapping
	public ResponseEntity<?> obtenerTodasEspecialidadesCentros() {
		List<EspecialidadCentro> especialidadesCentros = this.servicioEspecialidadCentro.obtenerTodas();
		if (especialidadesCentros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(especialidadesCentros);
	}

	@JsonView(Vistas.Lista.class)
	@GetMapping("/{nombreTitulacion}")
	public ResponseEntity<?> obtenerTodasEspecialidadesCentrosPorNombreTitulacion(
			@PathVariable String nombreTitulacion) {
		List<EspecialidadCentro> especialidadesCentros = this.servicioEspecialidadCentro
				.obtenerTodasPorNombreTitulacion(nombreTitulacion);
		if (especialidadesCentros.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(especialidadesCentros);
	}

//	@JsonView(Vistas.Lista.class)
//	@GetMapping("/lista/{idLista}")
//	public ResponseEntity<?> obtenerTodasEspecialidadesCentrosPorIdLista(@PathVariable Integer idLista) {
//		List<EspecialidadCentro> especialidadesCentros = this.servicioEspecialidadCentro
//				.obtenerTodasPorIdLista(idLista);
//		if (especialidadesCentros.isEmpty()) {
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok(especialidadesCentros);
//	}
}
