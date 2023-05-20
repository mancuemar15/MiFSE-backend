package com.mifse.backend.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.persistencia.modelos.Administrador;
import com.mifse.backend.persistencia.repositorios.RepositorioAdministrador;
import com.mifse.backend.servicios.ServicioAdministrador;
import com.mifse.backend.servicios.ServicioEmail;
import com.mifse.backend.servicios.ServicioTipoUsuario;

@Service
@Transactional
public class ServicioAdministradorImpl implements ServicioAdministrador {

	@Autowired
	private RepositorioAdministrador respositorioAdministrador;

	@Autowired
	private ServicioTipoUsuario servicioTipoUsuario;

	@Autowired
	private ServicioEmail servicioEmail;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Administrador guardar(Administrador administrador) {
		administrador.setContrasena(this.passwordEncoder.encode(administrador.getContrasena()));
		administrador.setTipoUsuario(this.servicioTipoUsuario.obtenerPorTipo("ADMINISTRADOR"));
		administrador.setHabilitado(true);
		this.servicioEmail.enviarEmailVerificacion(administrador.getNombre(), administrador.getEmail(),
				administrador.getId());
		return this.respositorioAdministrador.save(administrador);
	}

	@Override
	public Administrador actualizar(Administrador administrador) {
		Administrador administradorAActualizar = this.respositorioAdministrador.findById(administrador.getId()).get();

		administradorAActualizar.setNombre(administrador.getNombre());
		administradorAActualizar.setApellido1(administrador.getApellido1());
		administradorAActualizar.setApellido2(administrador.getApellido2());

		return this.respositorioAdministrador.save(administradorAActualizar);
	}

}
