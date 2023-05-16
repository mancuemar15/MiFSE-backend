package com.mifse.backend.servicios.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public Boolean verificarCorreoElectronico(Integer id) {
		Optional<Usuario> optionalUsuario = this.repositorioUsuario.findById(id);
		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			if (!usuario.isVerificado()) {
				usuario.setVerificado(true);
				this.actualizar(usuario);
				return true;
			}
		}
		return false;
	}

	private Usuario obtenerPorEmail(String email) {
		return this.repositorioUsuario.findByEmail(email);
	}

	@Override
	public Usuario obtenerPorEmailYContrasena(String email, String contrasena) {
		Usuario usuario = this.obtenerPorEmail(email);
		System.out.println(this.passwordEncoder.matches(contrasena, usuario.getContrasena()));
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
	public Usuario actualizar(Usuario usuario) {
		Usuario usuarioAActualizar = this.obtenerPorId(usuario.getId());

		usuarioAActualizar.setNombre(usuario.getNombre());
		usuarioAActualizar.setApellido1(usuario.getApellido1());
		usuarioAActualizar.setEmail(usuario.getEmail());
//		usuarioAActualizar.setNombre(usuario.getNombre());

		return this.repositorioUsuario.save(usuarioAActualizar);
	}

	@Override
	public Usuario bloquear(Integer id) {
		Usuario usuarioABloquear = this.obtenerPorId(id);

		usuarioABloquear.setInhabilitado(true);

		return this.repositorioUsuario.save(usuarioABloquear);
	}
}
