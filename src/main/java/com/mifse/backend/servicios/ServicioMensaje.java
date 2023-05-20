package com.mifse.backend.servicios;

import java.util.List;
import java.util.Set;

import com.mifse.backend.persistencia.modelos.Mensaje;
import com.mifse.backend.persistencia.modelos.Usuario;

public interface ServicioMensaje {

	public List<Mensaje> obtenerTodosPorIdEmisorYIdReceptor(Long idEmisor, Long idReceptor);

	public Set<Usuario> obtenerUsuariosConMensajesIntercambiados(Long idUsuario);

	public Set<Usuario> obtenerUsuariosConMensajesIntercambiadosSinLeer(Long idUsuario);

	public Mensaje guardar(Mensaje mensaje);

	public void marcarComoLeidos(List<Mensaje> mensajes);
}
