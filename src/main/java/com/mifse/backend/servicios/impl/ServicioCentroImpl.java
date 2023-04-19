package com.mifse.backend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.persistencia.modelos.Centro;
import com.mifse.backend.persistencia.repositorios.RepositorioCentro;
import com.mifse.backend.servicios.ServicioCentro;

@Service
@Transactional
public class ServicioCentroImpl implements ServicioCentro {

	@Autowired
	private RepositorioCentro repositorioCentro;
	
	@Override
	public List<Centro> obtenerPorNombre(String nombre) {
		return this.repositorioCentro.findByNombreContainingIgnoreCase(nombre);
	}

}
