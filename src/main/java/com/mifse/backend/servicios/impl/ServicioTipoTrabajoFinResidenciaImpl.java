package com.mifse.backend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifse.backend.persistencia.modelos.TipoTrabajoFinResidencia;
import com.mifse.backend.persistencia.repositorios.RepositorioTipoTrabajoFinResidencia;
import com.mifse.backend.servicios.ServicioTipoTrabajoFinResidencia;

@Service
public class ServicioTipoTrabajoFinResidenciaImpl implements ServicioTipoTrabajoFinResidencia {

	@Autowired
	private RepositorioTipoTrabajoFinResidencia repositorioTipoTrabajoFinResidencia;

	@Override
	public List<TipoTrabajoFinResidencia> obtenerTodos() {
		return this.repositorioTipoTrabajoFinResidencia.findAll();
	}

}
