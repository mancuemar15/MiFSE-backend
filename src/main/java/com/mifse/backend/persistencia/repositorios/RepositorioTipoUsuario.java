package com.mifse.backend.persistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.TipoUsuario;

public interface RepositorioTipoUsuario extends JpaRepository<TipoUsuario, Long> {

	TipoUsuario findByTipo(String tipo);
}