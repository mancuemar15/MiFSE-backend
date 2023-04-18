package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.Administrador;

public interface ServicioAdministrador {

	public List<Administrador> obtenerTodos();

	public Administrador obtenerPorId(Integer id);

	public Administrador guardar(Administrador administrador);

	public void eliminarPorId(Integer id);
}
