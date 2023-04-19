package com.mifse.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.UltimaPosicion;
import com.mifse.backend.servicios.ServicioUltimaPosicion;

@RestController
@RequestMapping("/ultimas-posiciones")
public class ControladorUltimaPosicion {

	@Autowired
	private ServicioUltimaPosicion servicioUltimaPosicion;

	@GetMapping
	public ResponseEntity<?> obtenerUltimasPosiciones() {
		List<UltimaPosicion> ultimasPosiciones = this.servicioUltimaPosicion.obtenerTodas();

		if (ultimasPosiciones.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(ultimasPosiciones);
	}
}
