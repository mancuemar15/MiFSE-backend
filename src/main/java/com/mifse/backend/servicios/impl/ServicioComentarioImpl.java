package com.mifse.backend.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.persistencia.modelos.Comentario;
import com.mifse.backend.persistencia.repositorios.RepositorioComentario;
import com.mifse.backend.servicios.ServicioComentario;

@Service
@Transactional
public class ServicioComentarioImpl implements ServicioComentario {

	@Autowired
	private RepositorioComentario repositorioComentario;

	@Override
	public Comentario guardar(Comentario comentario) {
		return this.repositorioComentario.save(comentario);
	}

	@Override
	public void eliminarPorId(Integer id) {
		this.repositorioComentario.deleteById(id);
	}

}
