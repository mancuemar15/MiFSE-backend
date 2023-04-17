package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.TipoUsuario;

public interface ServicioTipoUsuario {

	public List<TipoUsuario> obtenerTodos();

	public TipoUsuario obtenerPorId(Integer id);
}
