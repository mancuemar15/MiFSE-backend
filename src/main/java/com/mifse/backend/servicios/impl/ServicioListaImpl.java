package com.mifse.backend.servicios.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.persistencia.modelos.Lista;
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
		final Lista listaGuardada = this.repositorioLista
				.save(Lista.builder().residente(lista.getResidente()).fechaCreacion(lista.getFechaCreacion())
						.fechaActualizacion(lista.getFechaActualizacion()).nombre(lista.getNombre()).build());
		listaGuardada.setPreferencias(lista.getPreferencias().stream().map(p -> {
			p.setLista(listaGuardada);
			return p;
		}).collect(Collectors.toList()));

		return this.repositorioLista.save(listaGuardada);
	}

	@Override
	public void eliminarPorId(Integer id) {
		this.repositorioLista.deleteById(id);
	}

}
