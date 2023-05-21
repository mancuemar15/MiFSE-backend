package com.mifse.backend.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.excepciones.ComentarioNotFoundException;
import com.mifse.backend.excepciones.EliminacionComentarioException;
import com.mifse.backend.excepciones.GuardarComentarioException;
import com.mifse.backend.persistencia.modelos.Comentario;
import com.mifse.backend.persistencia.modelos.Residente;
import com.mifse.backend.persistencia.repositorios.RepositorioComentario;
import com.mifse.backend.servicios.ServicioCentro;
import com.mifse.backend.servicios.ServicioComentario;
import com.mifse.backend.servicios.ServicioUsuario;

@Service
@Transactional
public class ServicioComentarioImpl implements ServicioComentario {

	@Autowired
	private RepositorioComentario repositorioComentario;

	@Autowired
	private ServicioUsuario servicioUsuario;

	@Autowired
	private ServicioCentro servicioCentro;

	@Override
	public Comentario guardar(Comentario comentario) {
		comentario.setResidente((Residente) this.servicioUsuario.obtenerPorId(comentario.getResidente().getId()));
		try {
			Comentario comentarioGuardado = this.repositorioComentario.save(comentario);
			this.servicioCentro.actualizarValoracionMedia(comentario.getCentro().getId());
			return comentarioGuardado;
		} catch (Exception e) {
			throw new GuardarComentarioException("Error al guardar el comentario: " + e.getMessage());
		}
	}

	@Override
	public void eliminarPorId(Long id) {
		Comentario comentario = this.repositorioComentario.findById(id)
				.orElseThrow(() -> new ComentarioNotFoundException(id));
		try {
			Long idCentro = comentario.getCentro().getId();
			this.repositorioComentario.deleteById(id);
			this.repositorioComentario.flush();

			this.servicioCentro.actualizarValoracionMedia(idCentro);
		} catch (Exception e) {
			throw new EliminacionComentarioException(id);
		}
	}

}
