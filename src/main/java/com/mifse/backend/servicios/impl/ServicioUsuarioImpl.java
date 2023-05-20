package com.mifse.backend.servicios.impl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return this.repositorioUsuario.findById(id).get();
	}

	@Override
	public List<Usuario> obtenerTodos() {
		return this.repositorioUsuario.findAll();
	}

	@Override
	public Boolean verificarCorreoElectronico(Long id) {
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

	@Override
	public Optional<Usuario> obtenerPorEmail(String email) {
		return this.repositorioUsuario.findByEmail(email);
	}

	@Override
	public Usuario obtenerPorEmailYContrasena(String email, String contrasena) {
		Usuario usuario = this.obtenerPorEmail(email).get();
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

		if (Objects.nonNull(usuarioAActualizar.getNombre())) {
			usuarioAActualizar.setNombre(usuario.getNombre());
		}
		usuarioAActualizar.setApellido1(usuario.getApellido1());
		usuarioAActualizar.setEmail(usuario.getEmail());
		if (Objects.nonNull(usuarioAActualizar.getContrasena())) {
			usuarioAActualizar.setContrasena(this.passwordEncoder.encode(usuario.getContrasena()));
		}

		return this.repositorioUsuario.save(usuarioAActualizar);
	}

	@Override
	public Usuario bloquear(Long id) {
		Usuario usuarioABloquear = this.obtenerPorId(id);

		usuarioABloquear.setHabilitado(false);

		return this.repositorioUsuario.save(usuarioABloquear);
	}

	@Override
	public void eliminar(Long id, String contrasena) {
		Usuario usuarioAEliminar = this.obtenerPorId(id);

		if (Objects.nonNull(usuarioAEliminar)
				&& this.passwordEncoder.matches(contrasena, usuarioAEliminar.getContrasena())) {
			this.repositorioUsuario.deleteById(id);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return (UserDetails) this.obtenerPorEmail(email).orElseThrow(
				() -> new UsernameNotFoundException(MessageFormat.format("username {0} not found", email)));
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
