package com.mifse.backend.servicios.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifse.backend.persistencia.modelos.UltimaPosicion;
import com.mifse.backend.persistencia.repositorios.RepositorioUltimaPosicion;
import com.mifse.backend.servicios.ServicioUltimaPosicion;

@Service
@Transactional
public class ServicioUltimaPosicionImpl implements ServicioUltimaPosicion {

	@Autowired
	private RepositorioUltimaPosicion repositorioUltimaPosicion;

	@Override
	public List<UltimaPosicion> obtenerTodas() {
		return this.repositorioUltimaPosicion.findAll();
	}

}