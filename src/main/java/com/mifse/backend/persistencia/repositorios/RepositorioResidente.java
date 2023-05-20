package com.mifse.backend.persistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.Residente;

public interface RepositorioResidente extends JpaRepository<Residente, Long> {

}
