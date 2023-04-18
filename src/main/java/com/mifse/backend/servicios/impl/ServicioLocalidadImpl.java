package com.mifse.backend.servicios.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifse.backend.persistencia.modelos.Localidad;
import com.mifse.backend.persistencia.repositorios.RepositorioLocalidad;
import com.mifse.backend.servicios.ServicioLocalidad;

@Service
@Transactional
public class ServicioLocalidadImpl implements ServicioLocalidad {

	@Autowired
	private RepositorioLocalidad repositorioLocalidad;

	@Override
	public List<Localidad> obtenerTodas() {
		return this.repositorioLocalidad.findAll();
	}

	@Override
	public List<Localidad> obtenerTodasPorIdProvincia(Integer idProvincia) {
		return this.repositorioLocalidad.findAllByProvinciaId(idProvincia);
	}

	@Override
	public Localidad obtenerPorId(Integer id) {
		return this.repositorioLocalidad.findById(id).get();
	}
}
