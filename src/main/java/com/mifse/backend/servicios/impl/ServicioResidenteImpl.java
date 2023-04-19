package com.mifse.backend.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.persistencia.modelos.Residente;
import com.mifse.backend.persistencia.repositorios.RepositorioResidente;
import com.mifse.backend.servicios.ServicioResidente;

@Service
@Transactional
public class ServicioResidenteImpl implements ServicioResidente {

	private final String URL_VERIFICACION = "http://localhost:8090/usuarios/verificar-correo-electronico?id=";

	@Autowired
	private RepositorioResidente repositorioResidente;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Residente guardar(Residente residente) {
		residente.setContrasena(this.passwordEncoder.encode(residente.getContrasena()));
		Residente residenteGuardado = this.repositorioResidente.save(residente);
		return this.repositorioResidente.save(residenteGuardado);
	}

}
