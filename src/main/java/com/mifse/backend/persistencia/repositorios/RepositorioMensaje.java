package com.mifse.backend.persistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.Mensaje;

public interface RepositorioMensaje extends JpaRepository<Mensaje, Long> {

	public List<Mensaje> findAllByEmisorIdAndReceptorIdOrEmisorIdAndReceptorId(Long idEmisor, Long idReceptor,
			Long idReceptor2, Long idEmisor2);

	public List<Mensaje> findAllByEmisorId(Long idEmisor);

	public List<Mensaje> findAllByReceptorId(Long idReceptor);
}
