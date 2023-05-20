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

	@GetMapping("/centro/{idCentro}")
	public ResponseEntity<?> obtenerEspecialidadesPorIdCentro(@PathVariable Long idCentro) {
		List<Especialidad> especialidades = this.servicioEspecialidad.obtenerTodasPorIdCentro(idCentro);

		if (especialidades.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(especialidades);
		}
	}

}
