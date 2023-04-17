package com.mifse.backend.persistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.UltimaPosicion;

public interface RepositorioUltimaPosicion extends JpaRepository<UltimaPosicion, Integer> {

}
