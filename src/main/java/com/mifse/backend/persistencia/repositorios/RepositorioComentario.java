package com.mifse.backend.persistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.Comentario;

public interface RepositorioComentario extends JpaRepository<Comentario, Integer> {

}
