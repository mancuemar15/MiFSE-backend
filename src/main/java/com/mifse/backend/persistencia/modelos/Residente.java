package com.mifse.backend.persistencia.modelos;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.vistas.Vistas;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "residente")
@PrimaryKeyJoinColumn(name = "ID")
public class Residente extends Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Vistas.CentroSimplificado.class)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_tipo_residente")
	private TipoResidente tipoResidente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_titulacion")
	private Titulacion titulacion;

}
