package com.mifse.backend.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.persistencia.modelos.Administrador;
import com.mifse.backend.persistencia.repositorios.RepositorioAdministrador;
import com.mifse.backend.servicios.ServicioAdministrador;

@Service
@Transactional
public class ServicioAdministradorImpl implements ServicioAdministrador {

	@Autowired
	private RepositorioAdministrador respositorioAdministrador;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Administrador obtenerPorId(Integer id) {
		return this.respositorioAdministrador.findById(id).orElse(null);
	}

	@Override
	public Administrador guardar(Administrador administrador) {
		administrador.setContrasena(this.passwordEncoder.encode(administrador.getContrasena()));
		return this.respositorioAdministrador.save(administrador);
	}

	@Override
	public void eliminarPorId(Integer id) {
		this.respositorioAdministrador.deleteById(id);
	}

}
