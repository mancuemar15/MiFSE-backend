package com.mifse.backend.persistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.Localidad;

public interface RepositorioLocalidad extends JpaRepository<Localidad, Long> {

	public List<Localidad> findAllByProvinciaId(Integer idProvincia);
}
