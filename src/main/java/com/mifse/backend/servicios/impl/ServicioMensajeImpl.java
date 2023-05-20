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
	public List<Mensaje> obtenerTodosPorIdEmisorYIdReceptor(Long idEmisor, Long idReceptor) {
		return this.repositorioMensaje.findAllByEmisorIdAndReceptorIdOrEmisorIdAndReceptorId(idEmisor, idReceptor,
				idReceptor, idEmisor);
	}

	@Override
	public Set<Usuario> obtenerUsuariosConMensajesIntercambiados(Long idUsuario) {
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
	public Set<Usuario> obtenerUsuariosConMensajesIntercambiadosSinLeer(Long idUsuario) {
		List<Mensaje> mensajesRecibidos = this.repositorioMensaje.findAllByReceptorId(idUsuario);
		Set<Usuario> usuariosConMensajesNoLeidos = new HashSet<>();

		for (Mensaje mensaje : mensajesRecibidos) {
			Usuario emisor = mensaje.getEmisor();
			if (emisor != null && !mensaje.getLeido()) {
				usuariosConMensajesNoLeidos.add(emisor);
			}
		}

		return usuariosConMensajesNoLeidos;
	}

	@Override
	public Mensaje guardar(Mensaje mensaje) {
		mensaje.setLeido(false);
		return this.repositorioMensaje.save(mensaje);
	}

	@Override
	public void marcarComoLeidos(List<Mensaje> mensajes) {
		mensajes.forEach(m -> m.setLeido(true));
		this.repositorioMensaje.saveAll(mensajes);
	}

}
