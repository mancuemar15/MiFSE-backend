package com.mifse.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.EspecialidadCentro;
import com.mifse.backend.servicios.ServicioEspecialidadCentro;

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
}