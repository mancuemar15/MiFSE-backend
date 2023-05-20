package com.mifse.backend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.persistencia.modelos.TipoResidente;
import com.mifse.backend.persistencia.repositorios.RepositorioTipoResidente;
import com.mifse.backend.servicios.ServicioTipoResidente;

@Service
@Transactional
public class ServicioTipoResidenteImpl implements ServicioTipoResidente {

	@Autowired
	private RepositorioTipoResidente repositorioTipoResidente;

	@Override
	public List<TipoResidente> obtenerTodos() {
		return this.repositorioTipoResidente.findAll();
	}

}