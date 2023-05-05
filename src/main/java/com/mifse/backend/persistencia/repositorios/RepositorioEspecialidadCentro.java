package com.mifse.backend.persistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.EspecialidadCentro;

public interface RepositorioEspecialidadCentro extends JpaRepository<EspecialidadCentro, Integer> {

	public List<EspecialidadCentro> findAllByEspecialidadTitulacionNombre(String nombreTitulacion);
}
