package com.mifse.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifse.backend.persistencia.modelos.Autonomia;
import com.mifse.backend.servicios.ServicioAutonomia;

@RestController
@RequestMapping(path = "/autonomias")
public class ControladorAutonomias {

	@Autowired
	private ServicioAutonomia servicioAutonomia;

	@GetMapping
	public List<Autonomia> obtenerAutonomias() {
		return this.servicioAutonomia.obtenerTodas();
	}

	@GetMapping("/{id}")
	public Autonomia obtenerAutonomiaPorId(@PathVariable Integer id) {
		return this.servicioAutonomia.obtenerPorId(id);
	}
}
