package com.mifse.backend.servicios.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifse.backend.persistencia.modelos.Provincia;
import com.mifse.backend.persistencia.repositorios.RepositorioProvincia;
import com.mifse.backend.servicios.ServicioProvincia;

@Service
@Transactional
public class ServicioProvinciaImpl implements ServicioProvincia {

	@Autowired
	private RepositorioProvincia repositorioProvincia;

	@Override
	public List<Provincia> obtenerTodas() {
		return this.repositorioProvincia.findAll();
	}
	
	@Override
    public List<Provincia> obtenerTodasPorIdAutonomia(Integer idAutonomia) {
        return repositorioProvincia.findAllByAutonomiaId(idAutonomia);
    }

	@Override
	public Provincia obtenerPorId(Integer id) {
		return this.repositorioProvincia.findById(id).get();
	}

}
