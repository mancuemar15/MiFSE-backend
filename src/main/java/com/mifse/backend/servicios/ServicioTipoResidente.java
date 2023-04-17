package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.TipoResidente;

public interface ServicioTipoResidente {

	public List<TipoResidente> obtenerTodos();

	public TipoResidente obtenerPorId(Integer id);
}
