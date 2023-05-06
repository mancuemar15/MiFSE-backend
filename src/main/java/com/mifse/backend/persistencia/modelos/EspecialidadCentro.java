package com.mifse.backend.persistencia.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.vistas.Vistas;

import lombok.Data;

@Data
@Entity
@Table(name = "especialidad_centro")
@IdClass(EspecialidadCentroId.class)
public class EspecialidadCentro {

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_especialidad")
	@JsonView(Vistas.Lista.class)
	private Especialidad especialidad;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_centro")
	@JsonView(Vistas.Lista.class)
	private Centro centro;

	@Column(name = "Necesidad_coche")
	private Boolean necesidadCoche;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_tipo_trabajo_fin_residencia")
	private TipoTrabajoFinResidencia tipoTrabajoFinResidencia;

	@Column(name = "Hay_clases")
	private Boolean hayClases;

	@Column(name = "Hay_examenes")
	private Boolean hayExamenes;

	@Column(name = "Numero_dias_vacaciones")
	private Integer numeroDiasVacaciones;

	@Column(name = "Numero_dias_libre_disposicion")
	private Integer numeroDiasLibreDisposicion;

	@Column(name = "Numero_guardias_mes")
	private Integer numeroGuardiasMes;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_tipo_guardias_findes_festivos")
	private TipoGuardiasFindesFestivos tipoGuardiasFindesFestivos;

	@Column(name = "Hay_rotaciones_externas")
	private Boolean hayRotacionesExternas;

	@Column(name = "Sueldo")
	private Integer sueldo;
}