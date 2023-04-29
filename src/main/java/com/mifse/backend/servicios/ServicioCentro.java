package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.Centro;
import com.mifse.backend.persistencia.modelos.dto.CentroDTO;

public interface ServicioCentro {

	public Centro obtenerPorId(Integer id);

	public List<CentroDTO> obtenerPorNombre(String nombre);
}
