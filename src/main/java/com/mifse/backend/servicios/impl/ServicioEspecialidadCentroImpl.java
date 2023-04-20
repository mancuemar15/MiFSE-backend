package com.mifse.backend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.persistencia.modelos.EspecialidadCentro;
import com.mifse.backend.persistencia.repositorios.RepositorioEspecialidadCentro;
import com.mifse.backend.servicios.ServicioEspecialidadCentro;

@Service
@Transactional
public class ServicioEspecialidadCentroImpl implements ServicioEspecialidadCentro {

	@Autowired
	private RepositorioEspecialidadCentro repositorioEspecialidadCentro;

	@Override
	public List<EspecialidadCentro> obtenerTodos() {
		return this.repositorioEspecialidadCentro.findAll();
	}

}
