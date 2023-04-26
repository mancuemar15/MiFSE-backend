package com.mifse.backend.persistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.UltimaPosicion;

public interface RepositorioUltimaPosicion extends JpaRepository<UltimaPosicion, Integer> {

	public List<UltimaPosicion> findAllByEspecialidadTitulacionNombre(String nombreTitulacion);
}
