package com.mifse.backend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.excepciones.EspecialidadNotFoundException;
import com.mifse.backend.persistencia.modelos.Especialidad;
import com.mifse.backend.persistencia.repositorios.RepositorioEspecialidad;
import com.mifse.backend.servicios.ServicioEspecialidad;

@Service
@Transactional
public class ServicioEspecialidadImpl implements ServicioEspecialidad {

	@Autowired
	private RepositorioEspecialidad repositorioEspecialidad;

	@Override
	public List<Especialidad> obtenerTodasPorIdCentro(Long idCentro) {
		List<Especialidad> especialidades = this.repositorioEspecialidad.findAllByIdCentro(idCentro);
		if (especialidades.isEmpty()) {
			throw new EspecialidadNotFoundException(
					"No se encontraron especialidades para el centro con ID: " + idCentro);
		}
		return especialidades;
	}

	@Override
	public Especialidad obtenerPorId(Long id) {
		return this.repositorioEspecialidad.findById(id)
				.orElseThrow(() -> new EspecialidadNotFoundException("No se encontró la especialidad con ID: " + id));
	}

}
