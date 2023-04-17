package com.mifse.backend.persistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.TipoResidente;

public interface RepositorioTipoResidente extends JpaRepository<TipoResidente, Integer> {

}
