package com.mifse.backend.persistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.Mensaje;

public interface RepositorioMensaje extends JpaRepository<Mensaje, Integer> {

}
