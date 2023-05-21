package com.mifse.backend.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifse.backend.excepciones.ActualizacionResidenteException;
import com.mifse.backend.excepciones.CreacionResidenteException;
import com.mifse.backend.excepciones.EmailYaExistenteException;
import com.mifse.backend.excepciones.ResidenteNotFoundException;
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
		return this.repositorioResidente.findById(id).orElseThrow(() -> new ResidenteNotFoundException(id));
	}

	@Override
	public Residente crear(Residente residente) {
		try {
			residente.setContrasena(this.passwordEncoder.encode(residente.getContrasena()));
			residente.setTipoUsuario(this.servicioTipoUsuario.obtenerPorTipo("RESIDENTE"));
			residente.setHabilitado(true);
			Residente residenteGuardado = this.repositorioResidente.save(residente);
			this.servicioEmail.enviarEmailVerificacion(residenteGuardado.getNombre(), residenteGuardado.getEmail(),
					residenteGuardado.getId());
			return residenteGuardado;
		} catch (DataIntegrityViolationException ex) {
			throw new EmailYaExistenteException(residente.getEmail());
		} catch (Exception e) {
			throw new CreacionResidenteException();
		}
	}

	@Override
	public Residente actualizar(Residente residente) {
		Residente residenteAActualizar = this.repositorioResidente.findById(residente.getId())
				.orElseThrow(() -> new ResidenteNotFoundException(residente.getId()));

		residenteAActualizar.setNombre(residente.getNombre());
		residenteAActualizar.setApellido1(residente.getApellido1());
		residenteAActualizar.setApellido2(residente.getApellido2());
		residenteAActualizar.setTipoResidente(residente.getTipoResidente());

		try {
			return residenteAActualizar;
		} catch (Exception e) {
			throw new ActualizacionResidenteException();
		}
	}

}