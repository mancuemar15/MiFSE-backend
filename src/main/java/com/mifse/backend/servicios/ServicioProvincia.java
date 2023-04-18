package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.Provincia;

public interface ServicioProvincia {

	public List<Provincia> obtenerTodas();

	public List<Provincia> obtenerTodasPorIdAutonomia(Integer idAutonomia);

	public Provincia obtenerPorId(Integer id);
}
