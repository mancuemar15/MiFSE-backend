package com.mifse.backend.persistencia.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.TipoUsuario;

public interface RepositorioTipoUsuario extends JpaRepository<TipoUsuario, Long> {

	public Optional<TipoUsuario> findByTipo(String tipo);
}