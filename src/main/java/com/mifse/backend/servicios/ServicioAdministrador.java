package com.mifse.backend.servicios;

import com.mifse.backend.persistencia.modelos.Administrador;

public interface ServicioAdministrador {

	public Administrador crear(Administrador administrador);

	public Administrador actualizar(Administrador administrador);
}
