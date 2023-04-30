package com.mifse.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.Especialidad;
import com.mifse.backend.servicios.ServicioEspecialidad;

@RestController
@RequestMapping("/especialidades")
public class ControladorEspecialidad {

	@Autowired
	private ServicioEspecialidad servicioEspecialidad;

	@GetMapping
	public ResponseEntity<?> obtenerEspecialidades() {
		return ResponseEntity.ok(this.servicioEspecialidad.obtenerTodas());
	}

	@GetMapping("/centro/{idCentro}")
	public ResponseEntity<?> obtenerEspecialidadesPorIdCentro(@PathVariable Integer idCentro) {
		List<Especialidad> especialidades = this.servicioEspecialidad.obtenerTodasPorIdCentro(idCentro);

		if (especialidades.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(especialidades);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerEspecialidadPorId(@PathVariable Integer id) {
		Especialidad especialidad = this.servicioEspecialidad.obtenerPorId(id);

		if (especialidad == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(especialidad);
		}
	}
}
