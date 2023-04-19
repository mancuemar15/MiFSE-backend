package com.mifse.backend.persistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.Mensaje;

public interface RepositorioMensaje extends JpaRepository<Mensaje, Integer> {

	public List<Mensaje> findAllByEmisorIdAndReceptorId(Integer idEmisor, Integer idReceptor);

	public List<Mensaje> findAllByEmisorId(Integer idEmisor);

	public List<Mensaje> findAllByReceptorId(Integer idReceptor);
}
