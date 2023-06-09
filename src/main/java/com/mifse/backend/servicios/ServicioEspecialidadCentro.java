package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.EspecialidadCentro;

public interface ServicioEspecialidadCentro {

	List<EspecialidadCentro> obtenerTodasPorNombreTitulacion(String nombreTitulacion);

	List<EspecialidadCentro> obtenerTodasPorIdLista(Long idLista);
}
