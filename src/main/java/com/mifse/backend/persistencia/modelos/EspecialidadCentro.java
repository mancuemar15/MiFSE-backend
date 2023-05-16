package com.mifse.backend.persistencia.modelos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@JsonView({Vistas.ListaPreferencias.class, Vistas.Lista.class, Vistas.ListaExtendida.class})
	private Especialidad especialidad;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_centro")
	@JsonView({Vistas.ListaPreferencias.class, Vistas.Lista.class, Vistas.ListaExtendida.class})
	private Centro centro;

	@Column(name = "Necesidad_coche")
	@JsonView({Vistas.Lista.class, Vistas.ListaExtendida.class})
	private Boolean necesidadCoche;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_tipo_trabajo_fin_residencia")
	@JsonView({Vistas.Lista.class, Vistas.ListaExtendida.class})
	private TipoTrabajoFinResidencia tipoTrabajoFinResidencia;

	@Column(name = "Hay_clases")
	@JsonView({Vistas.Lista.class, Vistas.ListaExtendida.class})
	private Boolean hayClases;

	@Column(name = "Hay_examenes")
	@JsonView({Vistas.Lista.class, Vistas.ListaExtendida.class})
	private Boolean hayExamenes;

	@Column(name = "Numero_dias_vacaciones")
	@JsonView({Vistas.Lista.class, Vistas.ListaExtendida.class})
	private Integer numeroDiasVacaciones;

	@Column(name = "Numero_dias_libre_disposicion")
	@JsonView({Vistas.Lista.class, Vistas.ListaExtendida.class})
	private Integer numeroDiasLibreDisposicion;

	@Column(name = "Numero_guardias_mes")
	@JsonView({Vistas.Lista.class, Vistas.ListaExtendida.class})
	private Integer numeroGuardiasMes;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_tipo_guardias_findes_festivos")
	@JsonView({Vistas.Lista.class, Vistas.ListaExtendida.class})
	private TipoGuardiasFindesFestivos tipoGuardiasFindesFestivos;

	@Column(name = "Hay_rotaciones_externas")
	@JsonView({Vistas.Lista.class, Vistas.ListaExtendida.class})
	private Boolean hayRotacionesExternas;

	@Column(name = "Sueldo")
	@JsonView({Vistas.Lista.class, Vistas.ListaExtendida.class})
	private Integer sueldo;
	
	@OneToMany(mappedBy = "especialidadCentro")
	private List<Preferencia> preferencias;
}