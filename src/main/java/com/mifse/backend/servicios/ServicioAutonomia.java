package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.Autonomia;

public interface ServicioAutonomia {

	List<Autonomia> obtenerTodas();

	Autonomia obtenerPorId(Integer id);

}
