package com.mifse.backend.persistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.Centro;

public interface RepositorioCentro extends JpaRepository<Centro, Long> {

	public List<Centro> findByNombreContainingIgnoreCase(String nombre);
}
