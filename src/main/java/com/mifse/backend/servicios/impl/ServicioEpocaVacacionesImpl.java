package com.mifse.backend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifse.backend.persistencia.modelos.EpocaVacaciones;
import com.mifse.backend.persistencia.repositorios.RepositorioEpocaVacaciones;
import com.mifse.backend.servicios.ServicioEpocaVacaciones;

@Service
public class ServicioEpocaVacacionesImpl implements ServicioEpocaVacaciones {

	@Autowired
	private RepositorioEpocaVacaciones repositorioEpocaVacaciones;

	@Override
	public List<EpocaVacaciones> obtenerTodas() {
		return this.repositorioEpocaVacaciones.findAll();
	}

}
