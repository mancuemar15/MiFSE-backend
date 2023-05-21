package com.mifse.backend.servicios.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.excepciones.ActualizacionMensajeException;
import com.mifse.backend.excepciones.MensajeNotFoundException;
import com.mifse.backend.persistencia.modelos.Mensaje;
import com.mifse.backend.persistencia.modelos.Usuario;
import com.mifse.backend.persistencia.repositorios.RepositorioMensaje;
import com.mifse.backend.servicios.ServicioEmail;
import com.mifse.backend.servicios.ServicioMensaje;
import com.mifse.backend.servicios.ServicioUsuario;

@Service
@Transactional
public class ServicioMensajeImpl implements ServicioMensaje {

	@Autowired
	private RepositorioMensaje repositorioMensaje;

	@Autowired
	private ServicioEmail servicioEmail;

	@Autowired
	private ServicioUsuario servicioUsuario;

	@Override
	public List<Mensaje> obtenerTodosPorIdEmisorYIdReceptor(Long idEmisor, Long idReceptor) {
		List<Mensaje> mensajes = this.repositorioMensaje.findAllByEmisorIdAndReceptorIdOrEmisorIdAndReceptorId(idEmisor,
				idReceptor, idReceptor, idEmisor);
		if (mensajes.isEmpty()) {
			throw new MensajeNotFoundException("No se encontraron mensajes");
		}
		return mensajes;
	}

	@Override
	public Set<Usuario> obtenerUsuariosConMensajesIntercambiados(Long idUsuario) {
		List<Mensaje> mensajesEnviados = this.repositorioMensaje.findByEmisorId(idUsuario);
		List<Mensaje> mensajesRecibidos = this.repositorioMensaje.findByReceptorId(idUsuario);

		if (mensajesEnviados.isEmpty() && mensajesRecibidos.isEmpty()) {
			throw new MensajeNotFoundException(
					"No se encontraron mensajes ni enviados ni recibidos por el usuario con ID: " + idUsuario);
		}

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
		List<Mensaje> mensajesRecibidos = this.repositorioMensaje.findByReceptorId(idUsuario);

		if (mensajesRecibidos.isEmpty()) {
			throw new MensajeNotFoundException("No se encontraron mensajes del usuario con ID: " + idUsuario);
		}

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
		Mensaje mensajeGuardado = this.repositorioMensaje.save(mensaje);

		if (Objects.nonNull(mensajeGuardado)) {
			Usuario emisor = this.servicioUsuario.obtenerPorId(mensajeGuardado.getEmisor().getId());
			Usuario receptor = this.servicioUsuario.obtenerPorId(mensajeGuardado.getReceptor().getId());

			this.servicioEmail.enviarEmailNuevoMensaje(receptor.getNombre(), receptor.getEmail(), emisor.getNombre(),
					mensajeGuardado.getContenido());
		} else {
			throw new MensajeNotFoundException("No se pudo guardar el mensaje.");
		}

		return mensajeGuardado;

	}

	@Override
	public void marcarComoLeidos(List<Mensaje> mensajes) {
		mensajes.forEach(m -> m.setLeido(true));
		try {
			this.repositorioMensaje.saveAll(mensajes);
		} catch (Exception e) {
			throw new ActualizacionMensajeException("No se pudo marcar los mensajes como leídos.");
		}
	}

}
