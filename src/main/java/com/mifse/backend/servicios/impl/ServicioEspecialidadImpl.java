package com.mifse.backend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.persistencia.modelos.Especialidad;
import com.mifse.backend.persistencia.repositorios.RepositorioEspecialidad;
import com.mifse.backend.servicios.ServicioEspecialidad;

@Service
@Transactional
public class ServicioEspecialidadImpl implements ServicioEspecialidad {

	@Autowired
	private RepositorioEspecialidad repositorioEspecialidad;

	@Override
	public List<Especialidad> obtenerTodas() {
		return this.repositorioEspecialidad.findAll();
	}

	@Override
	public List<Especialidad> obtenerTodasPorIdCentro(Integer idCentro) {
		return this.repositorioEspecialidad.findAllByIdCentro(idCentro);
	}

	@Override
	public Especialidad obtenerPorId(Integer id) {
		return this.repositorioEspecialidad.findById(id).orElse(null);
	}

}
