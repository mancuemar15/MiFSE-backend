package com.mifse.backend.persistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.Titulacion;

public interface RepositorioTitulacion extends JpaRepository<Titulacion, Long> {

}
