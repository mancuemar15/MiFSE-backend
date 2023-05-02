package com.mifse.backend.servicios;

import java.sql.SQLIntegrityConstraintViolationException;

import com.mifse.backend.persistencia.modelos.Residente;

public interface ServicioResidente {

	public Residente guardar(Residente residente) throws Exception;

}
