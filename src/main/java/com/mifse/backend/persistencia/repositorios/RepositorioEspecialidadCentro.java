package com.mifse.backend.persistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mifse.backend.persistencia.modelos.EspecialidadCentro;
import com.mifse.backend.persistencia.modelos.EspecialidadCentroId;

public interface RepositorioEspecialidadCentro extends JpaRepository<EspecialidadCentro, EspecialidadCentroId> {

	public List<EspecialidadCentro> findAllByEspecialidadTitulacionNombre(String nombreTitulacion);
	
	@Query("SELECT ec FROM EspecialidadCentro ec JOIN ec.preferencias p JOIN p.lista l WHERE l.id = :idLista ORDER BY p.numero")
	public List<EspecialidadCentro> findAllByIdLista(@Param("idLista") Integer idLista);
}
