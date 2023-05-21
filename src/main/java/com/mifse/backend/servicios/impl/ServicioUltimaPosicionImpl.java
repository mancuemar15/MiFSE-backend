package com.mifse.backend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.excepciones.UltimaPosicionNotFoundException;
import com.mifse.backend.persistencia.modelos.UltimaPosicion;
import com.mifse.backend.persistencia.repositorios.RepositorioUltimaPosicion;
import com.mifse.backend.servicios.ServicioUltimaPosicion;

@Service
@Transactional
public class ServicioUltimaPosicionImpl implements ServicioUltimaPosicion {

	@Autowired
	private RepositorioUltimaPosicion repositorioUltimaPosicion;

	@Override
	public List<UltimaPosicion> obtenerTodasPorNombreTitulacion(String nombreTitulacion) {
		List<UltimaPosicion> ultimasPosiciones = this.repositorioUltimaPosicion
				.findAllByEspecialidadTitulacionNombre(nombreTitulacion);
		if (ultimasPosiciones.isEmpty()) {
			throw new UltimaPosicionNotFoundException(
					"No se encontraron últimas posiciones para la titulación: " + nombreTitulacion);
		}
		return ultimasPosiciones;
	}

}
