package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.Centro;

public interface ServicioCentro {

	public Centro obtenerPorId(Long id);

	public List<Centro> obtenerPorNombre(String nombre);

	public void actualizarValoracionMedia(Long idCentro);
}
