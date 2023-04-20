package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.Usuario;

public interface ServicioUsuario {

	public Usuario obtenerPorId(Integer id);

	public List<Usuario> obtenerTodos();

	public Boolean verificarCorreoElectronico(Integer id);

	public Usuario obtenerPorEmailYContrasena(String email, String contrasena);

	public Boolean existeEmail(String email);

	public void actualizar(Usuario usuario);

	public void eliminarPorId(Integer id);
}
