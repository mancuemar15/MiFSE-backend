package com.mifse.backend.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.persistencia.modelos.Residente;
import com.mifse.backend.persistencia.repositorios.RepositorioResidente;
import com.mifse.backend.servicios.ServicioEmail;
import com.mifse.backend.servicios.ServicioResidente;
import com.mifse.backend.servicios.ServicioTipoUsuario;

@Service
@Transactional
public class ServicioResidenteImpl implements ServicioResidente {

	@Autowired
	private RepositorioResidente repositorioResidente;

	@Autowired
	private ServicioTipoUsuario servicioTipoUsuario;

	@Autowired
	private ServicioEmail servicioEmail;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Residente obtenerPorId(Long id) {
		return this.repositorioResidente.findById(id).get();
	}

	@Override
	public Residente guardar(Residente residente) {
		residente.setContrasena(this.passwordEncoder.encode(residente.getContrasena()));
		residente.setTipoUsuario(this.servicioTipoUsuario.obtenerPorTipo("RESIDENTE"));
		residente.setHabilitado(true);
		Residente residenteGuardado = this.repositorioResidente.save(residente);

		this.servicioEmail.enviarEmailVerificacion(residenteGuardado.getNombre(), residenteGuardado.getEmail(),
				residenteGuardado.getId());

		return residenteGuardado;
	}

	@Override
	public Residente actualizar(Residente residente) {
		Residente residenteAActualizar = this.repositorioResidente.findById(residente.getId()).get();

		residenteAActualizar.setNombre(residente.getNombre());
		residenteAActualizar.setApellido1(residente.getApellido1());
		residenteAActualizar.setApellido2(residente.getApellido2());
		residenteAActualizar.setEmail(residente.getEmail());
		residenteAActualizar.setTipoResidente(residente.getTipoResidente());

		return residenteAActualizar;
	}

}