package com.mifse.backend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.persistencia.modelos.Autonomia;
import com.mifse.backend.persistencia.repositorios.RepositorioAutonomia;
import com.mifse.backend.servicios.ServicioAutonomia;

@Service
@Transactional
public class ServicioAutonomiaImpl implements ServicioAutonomia {

	@Autowired
	private RepositorioAutonomia repositorioAutonomia;

	@Override
	public List<Autonomia> obtenerTodas() {
		return this.repositorioAutonomia.findAll();
	}

	@Override
	public Autonomia obtenerPorId(Integer id) {
		return this.repositorioAutonomia.findById(id).get();
	}
}
