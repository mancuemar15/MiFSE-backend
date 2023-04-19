package com.mifse.backend.servicios.impl;

import java.util.List;

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
		return this.repositorioLista.save(lista);
	}

	@Override
	public void eliminarPorId(Integer idLista) {
		this.repositorioLista.deleteById(idLista);
	}

}
