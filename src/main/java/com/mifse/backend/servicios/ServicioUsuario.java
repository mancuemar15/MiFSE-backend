package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.Usuario;

public interface ServicioUsuario {

	public Usuario obtenerPorId(Long id);

	public List<Usuario> obtenerTodos();

	public void verificarUsuario(Long id);

	public boolean existeEmail(String email);

	public void bloquear(Long id);

	public void desbloquear(Long id);

	public Usuario obtenerPorEmail(String email);

	public void eliminar(Long id, String contrasena);

}
