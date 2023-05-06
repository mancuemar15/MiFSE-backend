package com.mifse.backend.servicios.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.persistencia.modelos.Lista;
import com.mifse.backend.persistencia.modelos.Preferencia;
import com.mifse.backend.persistencia.repositorios.RepositorioLista;
import com.mifse.backend.servicios.ServicioLista;

@Service
@Transactional
public class ServicioListaImpl implements ServicioLista {

	@Autowired
	private RepositorioLista repositorioLista;

	@Override
	public List<Lista> obtenerListasPorIdResidente(Integer idResidente) {
		return this.repositorioLista.findAllByResidenteId(idResidente);
	}

	@Override
	public Lista guardar(Lista lista) {
		final Lista nuevaLista = Lista.builder().residente(lista.getResidente()).fechaCreacion(lista.getFechaCreacion())
				.fechaActualizacion(lista.getFechaActualizacion()).build();

		if (lista.getId() == null) {
			final Lista listaGuardada = this.repositorioLista.save(nuevaLista);
			List<Preferencia> preferencias = lista.getPreferencias().stream().map(p -> {
				p.setLista(listaGuardada);
				return p;
			}).collect(Collectors.toList());
			nuevaLista.setPreferencias(preferencias);
		}

		return this.repositorioLista.save(nuevaLista);
	}

	@Override
	public void eliminarPorId(Integer id) {
		this.repositorioLista.deleteById(id);
	}

}
