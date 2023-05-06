package com.mifse.backend.persistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mifse.backend.persistencia.modelos.Especialidad;

public interface RepositorioEspecialidad extends JpaRepository<Especialidad, Integer> {

	@Query("SELECT e, t FROM EspecialidadCentro ec JOIN ec.especialidad e JOIN e.titulacion t WHERE ec.centro = :idCentro")
	public List<Especialidad> findAllByIdCentro(@Param("idCentro") Integer idCentro);
}
