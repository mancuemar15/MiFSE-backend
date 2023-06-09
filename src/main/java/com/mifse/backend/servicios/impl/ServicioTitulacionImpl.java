package com.mifse.backend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.excepciones.TitulacionNotFoundException;
import com.mifse.backend.persistencia.modelos.Titulacion;
import com.mifse.backend.persistencia.repositorios.RepositorioTitulacion;
import com.mifse.backend.servicios.ServicioTitulacion;

@Service
@Transactional
public class ServicioTitulacionImpl implements ServicioTitulacion {

	@Autowired
	private RepositorioTitulacion repositorioTitulacion;

	@Override
	public List<Titulacion> obtenerTodas() {
		List<Titulacion> titulaciones = this.repositorioTitulacion.findAll();
		if (titulaciones.isEmpty()) {
			throw new TitulacionNotFoundException();
		}
		return titulaciones;
	}

}
