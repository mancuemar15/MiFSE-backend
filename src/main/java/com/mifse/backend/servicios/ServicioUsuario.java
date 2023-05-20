package com.mifse.backend.servicios;

import java.util.List;
import java.util.Optional;

import com.mifse.backend.persistencia.modelos.Usuario;

public interface ServicioUsuario {

	public Usuario obtenerPorId(Long id);

	public List<Usuario> obtenerTodos();

	public Boolean verificarCorreoElectronico(Long id);

	public Usuario obtenerPorEmailYContrasena(String email, String contrasena);

	public Boolean existeEmail(String email);

	public Usuario actualizar(Usuario usuario);

	public Usuario bloquear(Long id);

	Optional<Usuario> obtenerPorEmail(String email);

	public void eliminar(Long id, String contrasena);

}
