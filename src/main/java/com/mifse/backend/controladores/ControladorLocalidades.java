package com.mifse.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.Localidad;
import com.mifse.backend.servicios.ServicioLocalidad;

@RestController
@RequestMapping(path = "/localidades")
public class ControladorLocalidades {

	@Autowired
	private ServicioLocalidad servicioLocalidad;

	@GetMapping
	public ResponseEntity<?> obtenerLocalidades() {
		return ResponseEntity.ok(this.servicioLocalidad.obtenerTodas());
	}

	@GetMapping("/provincia/{idProvincia}")
	public ResponseEntity<?> obtenerLocalidadesPorIdProvincia(@PathVariable Integer idProvincia) {
		return ResponseEntity.ok(this.servicioLocalidad.obtenerTodasPorIdProvincia(idProvincia));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerLocalidadPorId(@PathVariable Integer id) {
		Localidad localidad = this.servicioLocalidad.obtenerPorId(id);
		if (localidad != null) {
			return ResponseEntity.ok(localidad);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
