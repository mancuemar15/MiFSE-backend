package com.mifse.backend.persistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.Centro;
import com.mifse.backend.persistencia.modelos.dto.CentroIdNombreDTO;

public interface RepositorioCentro extends JpaRepository<Centro, Long> {

	public List<CentroIdNombreDTO> findByNombreContainingIgnoreCase(String nombre);
}
