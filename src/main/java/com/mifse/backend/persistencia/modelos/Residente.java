package com.mifse.backend.persistencia.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ID_tipo_residente")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private TipoResidente tipoResidente;

	@ManyToOne
	@JoinColumn(name = "ID_titulacion")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Titulacion titulacion;

}
