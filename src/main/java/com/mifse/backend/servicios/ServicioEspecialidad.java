package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.Especialidad;

public interface ServicioEspecialidad {

	public List<Especialidad> obtenerTodas();

	public Especialidad obtenerPorId(Integer id);
}
