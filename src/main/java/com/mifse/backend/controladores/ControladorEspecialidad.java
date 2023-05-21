package com.mifse.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mifse.backend.excepciones.EspecialidadNotFoundException;
import com.mifse.backend.persistencia.modelos.Especialidad;
import com.mifse.backend.servicios.ServicioEspecialidad;

@RestController
@RequestMapping("/especialidades")
public class ControladorEspecialidad {

	@Autowired
	private ServicioEspecialidad servicioEspecialidad;

	@GetMapping("/centro/{idCentro}")
	public ResponseEntity<List<Especialidad>> obtenerEspecialidadesPorIdCentro(@PathVariable Long idCentro) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(this.servicioEspecialidad.obtenerTodasPorIdCentro(idCentro));
		} catch (EspecialidadNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
