package com.mifse.backend.persistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mifse.backend.persistencia.modelos.Provincia;

public interface RepositorioProvincia extends JpaRepository<Provincia, Integer> {

}
