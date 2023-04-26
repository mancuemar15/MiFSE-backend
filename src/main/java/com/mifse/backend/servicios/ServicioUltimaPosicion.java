package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.UltimaPosicion;

public interface ServicioUltimaPosicion {

	public List<UltimaPosicion> obtenerTodasPorNombreTitulacion(String nombreTitulacion);
}
