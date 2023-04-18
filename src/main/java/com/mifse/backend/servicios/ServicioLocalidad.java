package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.Localidad;

public interface ServicioLocalidad {

	public List<Localidad> obtenerTodas();

	public List<Localidad> obtenerTodasPorIdProvincia(Integer idProvincia);

	public Localidad obtenerPorId(Integer id);
}
