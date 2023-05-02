package com.mifse.backend.persistencia.modelos;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "especialidad_centro")
public class EspecialidadCentro {

	@EmbeddedId
	private EspecialidadCentroId id;

	@ManyToOne
	@MapsId("idEspecialidad")
	@JoinColumn(name = "ID_especialidad")
	private Especialidad especialidad;

	@ManyToOne
	@MapsId("idCentro")
	@JoinColumn(name = "ID_centro")
	private Centro centro;

	@Column(name = "Necesidad_coche")
	private Boolean necesidadCoche;

	@ManyToOne
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

	@ManyToOne
	@JoinColumn(name = "ID_tipo_guardias_findes_festivos")
	private TipoGuardiasFindesFestivos tipoGuardiasFindesFestivos;

	@Column(name = "Hay_rotaciones_externas")
	private Boolean hayRotacionesExternas;

	@Column(name = "Numero_meses_rotacion_externa")
	private Integer numeroMesesRotacionExterna;

	@Column(name = "Sueldo_minimo")
	private Integer sueldoMinimo;

	@Column(name = "Sueldo_maximo")
	private Integer sueldoMaximo;
}