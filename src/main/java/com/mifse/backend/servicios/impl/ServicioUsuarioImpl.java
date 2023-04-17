package com.mifse.backend.servicios.impl;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mifse.backend.persistencia.modelos.Usuario;
import com.mifse.backend.persistencia.repositorios.RepositorioUsuario;
import com.mifse.backend.servicios.ServicioUsuario;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	@Autowired
	private RepositorioUsuario repositorioUsuario;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Usuario obtenerPorId(Integer id) {
		return this.repositorioUsuario.findById(id).get();
	}

	@Override
	public List<Usuario> obtenerTodos() {
		return this.repositorioUsuario.findAll();
	}

	private Usuario obtenerPorEmail(String email) {
		return this.repositorioUsuario.findByEmail(email);
	}

	@Override
	public Usuario obtenerPorEmailYContrasena(String email, String contrasena) {
		Usuario usuario = this.obtenerPorEmail(email);
		if (this.passwordEncoder.matches(contrasena, usuario.getContrasena())) {
			return usuario;
		}
		return null;
	}

	@Override
	public Boolean existeEmail(String email) {
		return this.repositorioUsuario.existsByEmail(email);
	}

	@Override
	public void guardar(Usuario usuario) {
		if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
			throw new IllegalArgumentException("El nombre es obligatorio");
		}
		if (usuario.getApellido1() == null || usuario.getApellido1().isEmpty()) {
			throw new IllegalArgumentException("El primer apellido es obligatorio");
		}
		if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
			throw new IllegalArgumentException("El email es obligatorio");
		}
		if (usuario.getContrasena() == null || usuario.getContrasena().isEmpty()) {
			throw new IllegalArgumentException("La contraseña es obligatoria");
		}

		if (this.repositorioUsuario.existsByEmail(usuario.getEmail())) {
			throw new IllegalArgumentException("El email ya está en uso");
		}

		usuario.setContrasena(this.passwordEncoder.encode(usuario.getContrasena()));
		usuario.setFechaAlta(new Date());
		this.repositorioUsuario.save(usuario);
	}

	@Override
	public void actualizar(Usuario usuario) {
		if (usuario.getId() == null) {
			throw new IllegalArgumentException("El usuario no tiene un ID válido");
		}

		this.repositorioUsuario.save(usuario);
	}

	@Override
	public void eliminarPorId(Integer id) {
		try {
			this.repositorioUsuario.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchElementException("No se encontró ninguna entidad con el ID proporcionado: " + id);
		}
	}
}
