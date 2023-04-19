package com.mifse.backend.servicios.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mifse.backend.persistencia.modelos.Residente;
import com.mifse.backend.persistencia.repositorios.RepositorioResidente;
import com.mifse.backend.servicios.ServicioResidente;

@Service
@Transactional
public class ServicioResidenteImpl implements ServicioResidente {

	@Autowired
	private RepositorioResidente repositorioResidente;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Residente guardar(Residente residente) {
		residente.setContrasena(this.passwordEncoder.encode(residente.getContrasena()));
		return this.repositorioResidente.save(residente);
	}

}
