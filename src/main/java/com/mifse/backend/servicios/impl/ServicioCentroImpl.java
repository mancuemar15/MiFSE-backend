package com.mifse.backend.servicios.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.excepciones.CentroNotFoundException;
import com.mifse.backend.excepciones.ValoracionMediaException;
import com.mifse.backend.persistencia.modelos.Centro;
import com.mifse.backend.persistencia.modelos.Comentario;
import com.mifse.backend.persistencia.repositorios.RepositorioCentro;
import com.mifse.backend.servicios.ServicioCentro;

@Service
@Transactional
public class ServicioCentroImpl implements ServicioCentro {

	@Autowired
	private RepositorioCentro repositorioCentro;

	@Override
	public Centro obtenerPorId(Long id) {
		return this.repositorioCentro.findById(id)
				.orElseThrow(() -> new CentroNotFoundException("No se encontró el centro con ID: " + id));
	}

	@Override
	public List<Centro> obtenerPorNombre(String nombre) {
		List<Centro> centros = this.repositorioCentro.findByNombreContainingIgnoreCase(nombre);

		if (centros.isEmpty()) {
			throw new CentroNotFoundException("No se encontraron centros con el nombre: " + nombre);
		}

		return centros;
	}

	@Override
	public void actualizarValoracionMedia(Long centroId) {
		Centro centro = obtenerPorId(centroId);
		if (Objects.nonNull(centro)) {
			try {
				Double valoracionMedia = calcularValoracionMedia(centro);
				centro.setValoracionMedia(valoracionMedia);
				this.repositorioCentro.save(centro);
			} catch (ValoracionMediaException e) {
				centro.setValoracionMedia(null);
				this.repositorioCentro.save(centro);
			} catch (Exception e) {
				throw new ValoracionMediaException(
						"Error al calcular la valoración media del centro con ID: " + centroId);
			}
		} else {
			throw new CentroNotFoundException("No se encontró el centro con ID: " + centroId);
		}
	}

	private Double calcularValoracionMedia(Centro centro) {
		Double valoracionMedia = null;
		List<Comentario> comentarios = centro.getComentarios();

		if (comentarios != null && !comentarios.isEmpty()) {
			valoracionMedia = comentarios.stream().mapToDouble(Comentario::getValoracion).average().orElseGet(null);
		} else {
			throw new ValoracionMediaException(
					"No hay comentarios disponibles para calcular la valoración media del centro con ID: "
							+ centro.getId());
		}

		return valoracionMedia;
	}
}
