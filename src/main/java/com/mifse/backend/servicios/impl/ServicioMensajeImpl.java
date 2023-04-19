package com.mifse.backend.servicios.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.persistencia.modelos.Mensaje;
import com.mifse.backend.persistencia.modelos.Usuario;
import com.mifse.backend.persistencia.repositorios.RepositorioMensaje;
import com.mifse.backend.servicios.ServicioMensaje;

@Service
@Transactional
public class ServicioMensajeImpl implements ServicioMensaje {

	@Autowired
	private RepositorioMensaje repositorioMensaje;

	@Override
	public List<Mensaje> obtenerTodosPorIdEmisorYIdReceptor(Integer idEmisor, Integer idReceptor) {
		return this.repositorioMensaje.findAllByEmisorIdAndReceptorId(idEmisor, idReceptor);
	}

	@Override
	public Set<Usuario> obtenerUsuariosConMensajesIntercambiados(Integer idUsuario) {
		List<Mensaje> mensajesEnviados = this.repositorioMensaje.findAllByEmisorId(idUsuario);
		List<Mensaje> mensajesRecibidos = this.repositorioMensaje.findAllByReceptorId(idUsuario);
		Set<Usuario> usuariosConMensajesIntercambiados = new HashSet<>();

		for (Mensaje mensaje : mensajesEnviados) {
			usuariosConMensajesIntercambiados.add(mensaje.getReceptor());
		}

		for (Mensaje mensaje : mensajesRecibidos) {
			usuariosConMensajesIntercambiados.add(mensaje.getEmisor());
		}

		return usuariosConMensajesIntercambiados;
	}

	@Override
	public Mensaje guardar(Mensaje mensaje) {
		return this.repositorioMensaje.save(mensaje);
	}

}
