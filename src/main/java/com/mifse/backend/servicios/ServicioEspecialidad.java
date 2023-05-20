package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.Especialidad;

public interface ServicioEspecialidad {

	public List<Especialidad> obtenerTodasPorIdCentro(Long idCentro);

	public Especialidad obtenerPorId(Long id);
}
