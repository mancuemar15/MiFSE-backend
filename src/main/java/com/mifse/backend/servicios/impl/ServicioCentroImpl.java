package com.mifse.backend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.persistencia.modelos.Centro;
import com.mifse.backend.persistencia.modelos.Comentario;
import com.mifse.backend.persistencia.modelos.dto.CentroIdNombreDTO;
import com.mifse.backend.persistencia.repositorios.RepositorioCentro;
import com.mifse.backend.servicios.ServicioCentro;

@Service
@Transactional
public class ServicioCentroImpl implements ServicioCentro {

	@Autowired
	private RepositorioCentro repositorioCentro;

	@Override
	public Centro obtenerPorId(Long id) {
		return this.repositorioCentro.findById(id).get();
	}

	@Override
	public List<CentroIdNombreDTO> obtenerPorNombre(String nombre) {
		return this.repositorioCentro.findByNombreContainingIgnoreCase(nombre);
	}

	@Override
	public void actualizarValoracionMedia(Long centroId) {
		Centro centro = obtenerPorId(centroId);
		if (centro != null) {
			Double valoracionMedia = calcularValoracionMedia(centro);
			centro.setValoracionMedia(valoracionMedia);
			this.repositorioCentro.save(centro);
		}
	}

	private Double calcularValoracionMedia(Centro centro) {
		Double valoracionMedia = null;
		List<Comentario> comentarios = centro.getComentarios();

		if (comentarios != null && !comentarios.isEmpty()) {
			valoracionMedia = comentarios.stream().mapToDouble(Comentario::getValoracion).average().orElse(0.0);
		}

		return valoracionMedia;
	}
}
