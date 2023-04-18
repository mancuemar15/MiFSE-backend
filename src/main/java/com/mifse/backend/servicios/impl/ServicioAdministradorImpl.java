package com.mifse.backend.servicios.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifse.backend.persistencia.modelos.Administrador;
import com.mifse.backend.persistencia.repositorios.RepositorioAdministrador;
import com.mifse.backend.servicios.ServicioAdministrador;

@Service
@Transactional
public class ServicioAdministradorImpl implements ServicioAdministrador {

	@Autowired
	private RepositorioAdministrador respositorioAdministrador;

	@Override
	public List<Administrador> obtenerTodos() {
		return this.respositorioAdministrador.findAll();
	}

	@Override
	public Administrador obtenerPorId(Integer id) {
		return this.respositorioAdministrador.findById(id).orElse(null);
	}

	@Override
	public Administrador guardar(Administrador administrador) {
		return this.respositorioAdministrador.save(administrador);
	}

	@Override
	public void eliminarPorId(Integer id) {
		this.respositorioAdministrador.deleteById(id);
	}

}
