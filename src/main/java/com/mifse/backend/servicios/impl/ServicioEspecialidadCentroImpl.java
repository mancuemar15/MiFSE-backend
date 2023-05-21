package com.mifse.backend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.excepciones.EspecialidadTitulacionNotFoundException;
import com.mifse.backend.persistencia.modelos.EspecialidadCentro;
import com.mifse.backend.persistencia.repositorios.RepositorioEspecialidadCentro;
import com.mifse.backend.servicios.ServicioEspecialidadCentro;

@Service
@Transactional
public class ServicioEspecialidadCentroImpl implements ServicioEspecialidadCentro {

	@Autowired
	private RepositorioEspecialidadCentro repositorioEspecialidadCentro;

	@Override
	public List<EspecialidadCentro> obtenerTodasPorNombreTitulacion(String nombreTitulacion) {
		List<EspecialidadCentro> especialidadesCentro = this.repositorioEspecialidadCentro
				.findAllByEspecialidadTitulacionNombre(nombreTitulacion);

		if (especialidadesCentro.isEmpty()) {
			throw new EspecialidadTitulacionNotFoundException(
					"No se encontraron especialidades para la titulación: " + nombreTitulacion);
		}

		return especialidadesCentro;
	}

	@Override
	public List<EspecialidadCentro> obtenerTodasPorIdLista(Long idLista) {
		List<EspecialidadCentro> especialidadesCentro = this.repositorioEspecialidadCentro.findAllByIdLista(idLista);

		if (especialidadesCentro.isEmpty()) {
			throw new EspecialidadTitulacionNotFoundException(
					"No se encontraron especialidades para la lista con ID: " + idLista);
		}

		return especialidadesCentro;
	}

}
