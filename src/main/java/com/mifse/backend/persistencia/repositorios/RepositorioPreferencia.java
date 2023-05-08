package com.mifse.backend.persistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.Preferencia;
import com.mifse.backend.persistencia.modelos.PreferenciaId;

public interface RepositorioPreferencia extends JpaRepository<Preferencia, PreferenciaId> {
	
	public void deleteAllByListaId(Integer idLista);
}
