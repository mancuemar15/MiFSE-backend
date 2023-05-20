package com.mifse.backend.persistencia.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);

	public Usuario findByEmailAndContrasena(String email, String contrasena);

	public Boolean existsByEmail(String email);
}
