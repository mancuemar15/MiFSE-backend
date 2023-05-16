package com.mifse.backend.persistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.Lista;

public interface RepositorioLista extends JpaRepository<Lista, Integer> {

	public List<Lista> findAllByResidenteId(Integer idResidente);
	
}
