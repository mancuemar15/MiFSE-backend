package com.mifse.backend.persistencia.modelos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.vistas.Vistas;

import lombok.Data;

@Data
@JsonView(Vistas.ListaPreferencias.class)
public class EspecialidadCentroId implements Serializable {

	private static final long serialVersionUID = -8565513644282660450L;

	private Long especialidad;

	private Long centro;

}
