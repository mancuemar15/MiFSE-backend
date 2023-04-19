package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.Centro;

public interface ServicioCentro {

	public List<Centro> obtenerPorNombre(String nombre);
}
