package com.mifse.backend.servicios;

import com.mifse.backend.persistencia.modelos.TipoUsuario;

public interface ServicioTipoUsuario {

	public TipoUsuario obtenerPorTipo(String tipo);
}
