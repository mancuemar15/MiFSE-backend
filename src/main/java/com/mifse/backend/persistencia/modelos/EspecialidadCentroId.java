package com.mifse.backend.persistencia.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class EspecialidadCentroId implements Serializable {

	private static final long serialVersionUID = -8565513644282660450L;
	
	@Column(name = "ID_especialidad")
	private Integer idEspecialidad;
	
	@Column(name = "ID_centro")
	private Integer idCentro;

}
