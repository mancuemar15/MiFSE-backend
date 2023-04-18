package com.mifse.backend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mifse.backend.persistencia.modelos.Titulacion;
import com.mifse.backend.persistencia.repositorios.RepositorioTitulacion;
import com.mifse.backend.servicios.ServicioTitulacion;

public class ServicioTitulacionImpl implements ServicioTitulacion {

	@Autowired
	private RepositorioTitulacion repositorioTitulacion;

	@Override
	public List<Titulacion> obtenerTodasTitulaciones() {
		return this.repositorioTitulacion.findAll();
	}

	@Override
	public Titulacion obtenerTitulacionPorId(Integer id) {
		return this.repositorioTitulacion.findById(id).get();
	}

}
