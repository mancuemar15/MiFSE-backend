package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.EpocaVacaciones;

public interface ServicioEpocaVacaciones {

	public List<EpocaVacaciones> obtenerTodas();
}
