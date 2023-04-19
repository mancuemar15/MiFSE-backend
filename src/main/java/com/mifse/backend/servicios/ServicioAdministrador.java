package com.mifse.backend.servicios;

import com.mifse.backend.persistencia.modelos.Administrador;

public interface ServicioAdministrador {

	public Administrador obtenerPorId(Integer id);

	public Administrador guardar(Administrador administrador);

	public void eliminarPorId(Integer id);
}
