package com.mifse.backend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.excepciones.BloqueoUsuarioException;
import com.mifse.backend.excepciones.DesbloqueoUsuarioException;
import com.mifse.backend.excepciones.EliminacionUsuarioException;
import com.mifse.backend.excepciones.UsuarioNotFoundException;
import com.mifse.backend.excepciones.VerificacionUsuarioException;
import com.mifse.backend.persistencia.modelos.Usuario;
import com.mifse.backend.persistencia.repositorios.RepositorioUsuario;
import com.mifse.backend.servicios.ServicioUsuario;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario, UserDetailsManager {

	@Autowired
	private RepositorioUsuario repositorioUsuario;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Usuario obtenerPorId(Long id) {
		return this.repositorioUsuario.findById(id)
				.orElseThrow(() -> new UsuarioNotFoundException("No encontrado usuario con ID: " + id));
	}

	@Override
	public List<Usuario> obtenerTodos() {
		List<Usuario> usuarios = this.repositorioUsuario.findAll();
		if (usuarios.isEmpty()) {
			throw new UsuarioNotFoundException("No existen usuarios.");
		}
		return usuarios;
	}

	@Override
	public void verificarUsuario(Long id) {
		Usuario usuario = this.obtenerPorId(id);
		try {
			if (!usuario.isVerificado()) {
				usuario.setVerificado(true);
				this.repositorioUsuario.save(usuario);
			}
		} catch (Exception e) {
			throw new VerificacionUsuarioException("No se ha podido verificar el usuario.");
		}
	}

	@Override
	public Usuario obtenerPorEmail(String email) {
		return this.repositorioUsuario.findByEmail(email)
				.orElseThrow(() -> new UsuarioNotFoundException("No encontrado usuario con email " + email));
	}

	@Override
	public boolean existeEmail(String email) {
		return this.repositorioUsuario.existsByEmail(email);
	}

	@Override
	public void bloquear(Long id) {
		Usuario usuarioABloquear = this.obtenerPorId(id);
		try {
			usuarioABloquear.setHabilitado(false);
			this.repositorioUsuario.save(usuarioABloquear);
		} catch (Exception e) {
			throw new BloqueoUsuarioException("No se ha podido bloquear al usuario con ID: " + id);
		}
	}

	@Override
	public void desbloquear(Long id) {
		Usuario usuarioADesbloquear = this.obtenerPorId(id);
		try {
			usuarioADesbloquear.setHabilitado(true);
			this.repositorioUsuario.save(usuarioADesbloquear);
		} catch (Exception e) {
			throw new DesbloqueoUsuarioException("No se ha podido desbloquear al usuario con ID: " + id);
		}
	}

	@Override
	public void eliminar(Long id, String contrasena) {
		Usuario usuarioAEliminar = this.obtenerPorId(id);
		try {
			if (this.passwordEncoder.matches(contrasena, usuarioAEliminar.getContrasena())) {
				this.repositorioUsuario.deleteById(id);
			}
		} catch (Exception e) {
			throw new EliminacionUsuarioException("No se ha podido eliminar el usuario con ID: " + id);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		return (UserDetails) this.obtenerPorEmail(email);
	}

	@Override
	public void createUser(UserDetails user) {
	}

	@Override
	public void updateUser(UserDetails user) {
	}

	@Override
	public void deleteUser(String username) {
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
	}

	@Override
	public boolean userExists(String email) {
		return this.existeEmail(email);
	}
}
