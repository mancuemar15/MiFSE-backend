package com.mifse.backend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifse.backend.excepciones.TipoGuardiasFindesFestivosNotFoundException;
import com.mifse.backend.persistencia.modelos.TipoGuardiasFindesFestivos;
import com.mifse.backend.persistencia.repositorios.RepositorioTipoGuardiasFindesFestivos;
import com.mifse.backend.servicios.ServicioTipoGuardiasFindesFestivos;

@Service
public class ServicioTipoGuardiasFindesFestivosImpl implements ServicioTipoGuardiasFindesFestivos {

	@Autowired
	private RepositorioTipoGuardiasFindesFestivos repositorioTipoGuardiasFindesFestivos;

	@Override
	public List<TipoGuardiasFindesFestivos> obtenerTodos() {
		List<TipoGuardiasFindesFestivos> tiposGuardiasFindesFestivos = this.repositorioTipoGuardiasFindesFestivos
				.findAll();

		if (tiposGuardiasFindesFestivos.isEmpty()) {
			throw new TipoGuardiasFindesFestivosNotFoundException();
		}

		return tiposGuardiasFindesFestivos;
	}

}
