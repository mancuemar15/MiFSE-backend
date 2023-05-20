package com.mifse.backend.servicios;

import com.mifse.backend.persistencia.modelos.Residente;

public interface ServicioResidente {

	public Residente obtenerPorId(Long id);

	public Residente guardar(Residente residente);

	public Residente actualizar(Residente residente);
}