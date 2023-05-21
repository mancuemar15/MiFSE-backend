package com.mifse.backend.servicios.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.excepciones.ActualizacionListaException;
import com.mifse.backend.excepciones.CreacionListaException;
import com.mifse.backend.excepciones.EliminacionListaException;
import com.mifse.backend.excepciones.ListaNotFoundException;
import com.mifse.backend.persistencia.modelos.Lista;
import com.mifse.backend.persistencia.modelos.Preferencia;
import com.mifse.backend.persistencia.repositorios.RepositorioLista;
import com.mifse.backend.servicios.ServicioLista;
import com.mifse.backend.servicios.ServicioResidente;

@Service
@Transactional
public class ServicioListaImpl implements ServicioLista {

	@Autowired
	private RepositorioLista repositorioLista;

	@Autowired
	private ServicioResidente servicioResidente;

	@PostAuthorize("authentication.principal.id == returnObject.residente.id")
	@Override
	public Lista obtenerPorIdOrdenadoPorNumeroPreferencia(Long id) {
		Lista lista = this.repositorioLista.findById(id)
				.orElseThrow(() -> new ListaNotFoundException("No se encontró la lista con ID: " + id));
		List<Preferencia> preferencias = lista.getPreferencias();
		Collections.sort(preferencias, Comparator.comparingInt(Preferencia::getNumero));
		lista.setPreferencias(preferencias);
		return lista;
	}

	@Override
	public List<Lista> obtenerListasPorIdResidente(Long idResidente) {
		List<Lista> listas = this.repositorioLista.findAllByResidenteId(idResidente);
		if (listas.isEmpty()) {
			throw new ListaNotFoundException("No se encontraron listas para el residente con ID: " + idResidente);
		}
		return listas;
	}

	@Override
	public Lista crear(Lista lista) {
		try {
			final Lista listaGuardada = this.repositorioLista
					.save(Lista.builder().residente(this.servicioResidente.obtenerPorId(lista.getResidente().getId()))
							.fechaCreacion(lista.getFechaCreacion()).fechaActualizacion(lista.getFechaActualizacion())
							.nombre(lista.getNombre()).build());

			listaGuardada.setPreferencias(lista.getPreferencias().stream().map(preferencia -> {
				preferencia.setLista(listaGuardada);
				return preferencia;
			}).collect(Collectors.toList()));

			return this.repositorioLista.save(listaGuardada);
		} catch (Exception e) {
			throw new CreacionListaException();
		}
	}

	@Override
	public Lista actualizar(Lista lista) {
		Lista listaAActualizar = this.repositorioLista.findById(lista.getId())
				.orElseThrow(() -> new ListaNotFoundException("No se encontró la lista con ID: " + lista.getId()));

		try {
			listaAActualizar.getPreferencias().clear();
			listaAActualizar.setNombre(lista.getNombre());
			listaAActualizar.setFechaActualizacion(lista.getFechaActualizacion());

			if (Objects.nonNull(lista.getPreferencias())) {
				for (Preferencia preferencia : lista.getPreferencias()) {
					preferencia.setLista(listaAActualizar);
					listaAActualizar.getPreferencias().add(preferencia);
				}
			}

			return this.repositorioLista.save(listaAActualizar);
		} catch (Exception e) {
			throw new ActualizacionListaException(lista.getId());
		}
	}

	@PreAuthorize("@servicioListaImpl.obtenerPorIdOrdenadoPorNumeroPreferencia(#id).residente.id == authentication.principal.id")
	@Override
	public void eliminarPorId(Long id) {
		if (!this.repositorioLista.existsById(id)) {
			throw new ListaNotFoundException("No se encontró la lista con ID: " + id);
		}
		try {
			this.repositorioLista.deleteById(id);
		} catch (Exception e) {
			throw new EliminacionListaException(id);
		}
	}

}
