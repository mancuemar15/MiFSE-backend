package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.Centro;
import com.mifse.backend.persistencia.modelos.dto.CentroIdNombreDTO;

public interface ServicioCentro {

	public Centro obtenerPorId(Integer id);

	public List<CentroIdNombreDTO> obtenerPorNombre(String nombre);
	
	public void actualizarValoracionMedia(Integer idCentro);
}
