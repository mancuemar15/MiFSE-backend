package com.mifse.backend.servicios.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifse.backend.persistencia.modelos.TipoUsuario;
import com.mifse.backend.persistencia.repositorios.RepositorioTipoUsuario;
import com.mifse.backend.servicios.ServicioTipoUsuario;

@Service
@Transactional
public class ServicioTipoUsuarioImpl implements ServicioTipoUsuario {

	@Autowired
	private RepositorioTipoUsuario repositorioTipoUsuario;

	@Override
	public TipoUsuario obtenerPorId(Integer id) {
		return this.repositorioTipoUsuario.findById(id).get();
	}

	@Override
	public List<TipoUsuario> obtenerTodos() {
		return this.repositorioTipoUsuario.findAll();
	}

}